package com.zhenhui.demo.falcon.core.support.handler;

import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDataHandler extends AbstractHandler<Position> {

    public AbstractDataHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Connection connection, Position position) throws Exception {
        Position result = handlePosition(position);
        if (result != null) {
            ctx.fireChannelRead(result);
        }
    }

    protected abstract Position handlePosition(Position position);
}
