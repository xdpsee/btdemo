package com.zhenhui.demo.falcon.core.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Event extends Attributes {

    private UniqueID deviceId;

    private Long positionId;

    private EventType type;

    private Date time;

    public Event(EventType type, UniqueID deviceId, Long positionId) {
        this.type = type;
        this.positionId = positionId;
        this.time = new Date();
        this.deviceId = deviceId;
    }

    // 扩展属性

    public static final String SPEED = "speed";
}
