package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.ConnectionManager;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import lombok.Setter;

public abstract class AbstractServerConnector implements ServerConnector {

    private final ConnectionManager connectionManager = new DefaultConnectionManager();

    protected final Context context;

    @Setter
    private int port;

    public AbstractServerConnector(Context context) {
        this.context = context;
    }

    @Override
    public ConnectionManager connectionManager() {
        return connectionManager;
    }

    @Override
    public final int getPort() {
        return port;
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








