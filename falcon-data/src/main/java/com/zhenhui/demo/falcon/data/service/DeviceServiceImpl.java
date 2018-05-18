package com.zhenhui.demo.falcon.data.service;

import java.util.Set;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.service.DeviceService;
import org.springframework.stereotype.Component;

@Component
public class DeviceServiceImpl implements DeviceService {

    @Override
    public Set<CommandType> getSupportedCommands(long deviceId) {
        return null;
    }
}

