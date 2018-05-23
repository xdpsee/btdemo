package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.manager.DeviceManager;
import com.zhenhui.demo.falcon.core.manager.EventManager;
import com.zhenhui.demo.falcon.core.manager.PositionManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public final class Context {

    @Getter @NonNull
    private final Configs configs;
    
    @Getter @NonNull
    private final DeviceManager deviceManager;

    @Getter @NonNull
    private final PositionManager positionManager;

    @Getter @NonNull
    private final EventManager eventManager;
}
