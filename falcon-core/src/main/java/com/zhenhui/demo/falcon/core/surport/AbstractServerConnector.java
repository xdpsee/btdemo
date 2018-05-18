package com.zhenhui.demo.falcon.core.surport;

import com.zhenhui.demo.falcon.core.domain.ConnectionManager;
import com.zhenhui.demo.falcon.core.server.ServerConnector;

public abstract class AbstractServerConnector implements ServerConnector {

    private final ConnectionManager connectionManager = new DefaultConnectionManager();

    @Override
    public ConnectionManager connectionManager() {
        return connectionManager;
    }

    @Override
    public final void close() {
        try {
            connectionManager.closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}








