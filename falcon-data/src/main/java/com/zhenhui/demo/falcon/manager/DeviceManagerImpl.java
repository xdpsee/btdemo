package com.zhenhui.demo.falcon.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Model;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.manager.DeviceManager;
import com.zhenhui.demo.falcon.manager.dao.DeviceDAO;
import com.zhenhui.demo.falcon.manager.dao.ModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("deviceManagerImpl")
public class DeviceManagerImpl implements DeviceManager {

    @Autowired
    private ModelDAO modelDAO;
    @Autowired
    private DeviceDAO deviceDAO;

    @Override
    public Set<CommandType> getSupportedCommands(UniqueID deviceId) {

        final Device device = deviceDAO.queryDevice(deviceId);
        if (device == null) {
            return new HashSet<>();
        }

        final Model model = modelDAO.queryModel(device.getModel());
        return new HashSet<>(model != null ? model.getSupportedCommands() : new ArrayList<>());
    }

    @Override
    public Device getDevice(UniqueID deviceId) {
        return deviceDAO.queryDevice(deviceId);
    }
}


