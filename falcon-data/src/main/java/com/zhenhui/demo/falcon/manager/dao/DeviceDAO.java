package com.zhenhui.demo.falcon.manager.dao;

import java.util.Map;

import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Model;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.exception.DeviceNotFoundException;
import com.zhenhui.demo.falcon.core.support.exception.DuplicateDeviceException;
import com.zhenhui.demo.falcon.core.support.exception.ModelNotFoundException;
import com.zhenhui.demo.falcon.core.support.exception.ProtocolException;
import com.zhenhui.demo.falcon.manager.mybatis.mapper.DeviceMapper;
import com.zhenhui.demo.falcon.manager.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@SuppressWarnings({"SpringJavaAutowiringInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@Component
@CacheConfig(cacheNames = "devices")
public class DeviceDAO {

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private DeviceMapper deviceMapper;

    @CacheEvict(key = "#device.id")
    public void createDevice(Device device) throws ModelNotFoundException, ProtocolException, DuplicateDeviceException {

        final Model model = modelDAO.queryModel(device.getModel());
        if (null == model) {
            throw new ModelNotFoundException(String.format("name %s not found", device.getModel()));
        }

        if (!model.getProtocol().equals(device.getProtocol())) {
            throw new ProtocolException(
                String.format("protocol mismatch, %s-%s", model.getProtocol(), device.getProtocol()));
        }

        try {
            deviceMapper.insert(device);
        } catch (Exception e) {
            if (ExceptionUtils.hasDuplicateEntryException(e)) {
                throw new DuplicateDeviceException(String.format("duplicate device : %s", device.getId()));
            }

            throw e;
        }
    }

    @Cacheable(key = "#deviceId")
    public Device queryDevice(UniqueID deviceId) {
        return deviceMapper.selectById(deviceId);
    }

    @CacheEvict(key = "#deviceId", condition = "#result==true")
    public boolean updateAttributes(UniqueID deviceId, Map<String, Object> attributes) throws DeviceNotFoundException {
        final Device device = deviceMapper.selectById(deviceId);
        if (device != null) {
            Map<String, Object> currAttrs = device.getAttributes();
            currAttrs.putAll(attributes);
            return deviceMapper.updateAttributes(deviceId, currAttrs, device.getGmtUpdate()) > 0;
        } else {
            throw new DeviceNotFoundException(String.format("device %s not found", deviceId));
        }
    }
}

