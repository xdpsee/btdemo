package com.zhenhui.demo.falcon.core.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.domain.ConnectionManager;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class DefaultConnectionManager implements ConnectionManager {

    private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Map<UniqueID, Connection> connectionMap = new ConcurrentHashMap<>();

    @Override
    public void register(Connection connection) {
        channelGroup.add(connection.getChannel());
    }

    @Override
    public Connection lookup(UniqueID deviceId) {
        return null;
    }

    @Override
    public void closeAll() {
        channelGroup.close().syncUninterruptibly();
    }
}
