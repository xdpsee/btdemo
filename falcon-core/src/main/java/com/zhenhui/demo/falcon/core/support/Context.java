package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.service.DeviceService;
import com.zhenhui.demo.falcon.core.service.EventService;
import com.zhenhui.demo.falcon.core.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class Context {

    @Getter
    private final Configs configs;

    @Getter
    private final DeviceService deviceService;

    @Getter
    private final PositionService positionService;

    @Getter
    private final EventService eventService;

}
