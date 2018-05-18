package com.zhenhui.demo.falcon.core.domain;

public interface Message {

    UniqueID deviceId();

    byte[] rawBytes();

}

