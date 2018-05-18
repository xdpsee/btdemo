package com.zhenhui.demo.falcon.core.server;

import com.zhenhui.demo.falcon.core.domain.ConnectionManager;
import io.netty.channel.ChannelPipeline;

public interface ServerConnector {

    int getPort();

    void initPipeline(ChannelPipeline pipeline);

    ConnectionManager connectionManager();

    void close();
}

