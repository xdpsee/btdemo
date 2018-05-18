package com.zhenhui.demo.falcon.core.support.handler;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.support.Context;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDataHandler extends AbstractHandler<Position> {

    public AbstractDataHandler(Context context) {
        super(context);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Position position) throws Exception {
        Position result = handlePosition(position);
        if (result != null) {
            ctx.fireChannelRead(result);
        }
    }

    protected abstract Position handlePosition(Position position);
}
