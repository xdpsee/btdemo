package com.zhenhui.demo.falcon.core.support.service;

import java.util.Set;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.UniqueID;

public interface DeviceService {

    Device getDevice(UniqueID deviceId);

    Set<CommandType> getSupportedCommands(UniqueID deviceId);

}

