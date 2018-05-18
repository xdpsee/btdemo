package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;

public final class PipelineInitializer<C extends Channel> extends ChannelInitializer<C> {

    private final ServerConnector connector;

    public PipelineInitializer(ServerConnector connector) {
        this.connector = connector;
    }

    @Override
    protected void initChannel(C channel) throws Exception {

        channel.pipeline()
            .addLast("channel-open", new OpenChannelHandler(connector));

        connector.initPipeline(channel.pipeline());
    }

    private class OpenChannelHandler extends ChannelInboundHandlerAdapter {
        private final ServerConnector connector;

        OpenChannelHandler(ServerConnector connector) {
            this.connector = connector;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            if (!(ctx.channel() instanceof DatagramChannel)) {
                this.connector.connectionManager().register(new Connection(ctx.channel()));
            }
        }

    }
}
