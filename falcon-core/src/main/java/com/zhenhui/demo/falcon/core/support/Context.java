package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.support.service.DeviceService;
import com.zhenhui.demo.falcon.core.support.service.EventService;
import com.zhenhui.demo.falcon.core.support.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public final class Context {

    @Getter @NonNull
    private final Configs configs;
    
    @Getter @NonNull
    private final DeviceService deviceService;

    @Getter @NonNull
    private final PositionService positionService;

    @Getter @NonNull
    private final EventService eventService;
}
