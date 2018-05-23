package com.zhenhui.demo.falcon.core.server;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.domain.ConnectionManager;
import com.zhenhui.demo.falcon.core.domain.Protocol;
import com.zhenhui.demo.falcon.core.support.Context;
import io.netty.channel.ChannelPipeline;

public interface ServerConnector {

    int getPort();

    Context getContext();

    Configs getConfigs();

    Protocol getProtocol();

    void initPipeline(ChannelPipeline pipeline);

    ConnectionManager getConnectionManager();

    void close();
}


