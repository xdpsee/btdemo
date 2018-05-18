package com.zhenhui.demo.falcon.core.surport.handler;

import com.zhenhui.demo.falcon.core.domain.Position;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDataHandler extends AbstractHandler<Position> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Position position) throws Exception {
        Position result = handlePosition(position);
        if (result != null) {
            ctx.fireChannelRead(result);
        }
    }

    protected abstract Position handlePosition(Position position);
}
