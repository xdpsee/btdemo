package com.zhenhui.demo.falcon.core.support;

import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttribute;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttributesUtils;
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
            .addFirst("channel-open", new OpenChannelHandler(connector));

        connector.initPipeline(channel.pipeline());
    }

    private class OpenChannelHandler extends ChannelInboundHandlerAdapter {
        private final ServerConnector connector;

        OpenChannelHandler(ServerConnector connector) {
            this.connector = connector;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            if (!(ctx.channel() instanceof DatagramChannel)) {
                final Connection connection = new Connection(ctx.channel(), connector.getProtocol());
                ChannelAttributesUtils.set(ctx, ChannelAttribute.CONNECTION, connection);
                this.connector.getConnectionManager().register(connection);
            }
        }

    }
}
