package com.zhenhui.demo.falcon.core.domain;

public interface ConnectionManager {

    void register(Connection connection);

    Connection lookup(UniqueID deviceId);

    void closeAll();
}

