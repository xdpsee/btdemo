package com.zhenhui.demo.falcon.core.manager;

import java.util.Set;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.UniqueID;

public interface DeviceManager {

    Device getDevice(UniqueID deviceId);

    Set<CommandType> getSupportedCommands(UniqueID deviceId);

}

