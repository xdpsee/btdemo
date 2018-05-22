package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.manager.DeviceManager;
import com.zhenhui.demo.falcon.core.manager.EventManager;
import com.zhenhui.demo.falcon.core.manager.PositionManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class Context {

    @Getter
    private final Configs configs;

    @Getter
    private final DeviceManager deviceManager;

    @Getter
    private final PositionManager positionManager;

    @Getter
    private final EventManager eventManager;

}
