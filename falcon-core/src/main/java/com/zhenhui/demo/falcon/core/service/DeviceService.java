package com.zhenhui.demo.falcon.core.service;

import java.util.Set;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;

public interface DeviceService {

    Device queryDevice(UniqueID deviceId);

    Set<CommandType> getSupportedCommands(UniqueID deviceId);

    Position queryLastPosition(UniqueID deviceId);
}

