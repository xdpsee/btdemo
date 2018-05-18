package com.zhenhui.demo.falcon.service;

import java.util.Set;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.service.DeviceService;
import org.springframework.stereotype.Component;

@Component("deviceServiceImpl")
public class DeviceServiceImpl implements DeviceService {

    @Override
    public Set<CommandType> getSupportedCommands(UniqueID deviceId) {
        return null;
    }

    @Override
    public Device queryDevice(UniqueID deviceId) {
        return null;
    }

    @Override
    public Position queryLastPosition(UniqueID deviceId) {
        return null;
    }
}

