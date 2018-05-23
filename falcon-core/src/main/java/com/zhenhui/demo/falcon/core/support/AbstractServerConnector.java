package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.domain.ConnectionManager;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractServerConnector implements ServerConnector, InitializingBean {

    private final ConnectionManager connectionManager = new DefaultConnectionManager();

    protected final Context context;

    @Setter
    private Configs configs;

    @Setter
    private int port;

    public AbstractServerConnector(Context context) {
        this.context = context;
    }

    @Override
    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Override
    public final int getPort() {
        return port;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Configs getConfigs() {
        return this.configs != null ? this.configs : context.getConfigs();
    }

    @Override
    public final void close() {
        try {
            connectionManager.closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() {

        if (null == this.configs) {
            this.configs = new Configs();
        }

        context.getConfigs().getAttributes().forEach((key, value) -> {
            this.configs.getAttributes().putIfAbsent(key, value);
        });
    }
}








