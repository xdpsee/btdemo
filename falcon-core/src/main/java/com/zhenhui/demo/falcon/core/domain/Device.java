package com.zhenhui.demo.falcon.core.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Device extends Attributes {

    private UniqueID id;

    private String model;

    private String protocol;

    private Date gmtCreate;

    private Date gmtUpdate;

    // 扩展属性

    public static final String KEY_LAST_POSITION = "last.pos";

    public static final String KEY_SPEED_LIMIT = "speed.limit";

    public static final String KEY_MAINTENANCE_START = "maintenance.start";

    public static final String KEY_MAINTENANCE_INTERVAL = "maintenance.interval";

}
