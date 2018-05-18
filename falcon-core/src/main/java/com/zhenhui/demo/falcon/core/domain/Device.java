package com.zhenhui.demo.falcon.core.domain;

import lombok.Data;

@Data
public class Device extends Attributes {

    public static final String KEY_LAST_POSITION = "last-pos";

    private UniqueID id;

    private String protocol;

    private String model;

    private String name;

    private String phone;

    private String contacts;



}
