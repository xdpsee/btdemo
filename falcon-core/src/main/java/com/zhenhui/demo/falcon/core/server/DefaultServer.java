package com.zhenhui.demo.falcon.core.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zhenhui.demo.falcon.core.surport.PipelineInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultServer implements Server {

    private static final Logger logger = LoggerFactory.getLogger("DefaultServer");

    private final List<ServerConnector> connectors;
    private List<ChannelFuture> channels = new ArrayList<>();
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workGroup;

    private boolean shutdownCalled = false;

    public DefaultServer(final ServerConnector connector) {
        this(Arrays.asList(connector));
    }

    public DefaultServer(final List<ServerConnector> connectors) {
        this.connectors = connectors;
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
    }

    public void start() {

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        logger.info(String.format("Starting new server of type \"%s\"...", this.getClass().getSimpleName()));

        try {
            for (final ServerConnector connector : connectors) {
                final ServerBootstrap bootstrap =
                    new ServerBootstrap()
                        .group(bossGroup, workGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new PipelineInitializer<>(connector))
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, false);
                channels.add(bootstrap.bind(connector.getPort()).sync());
            }
        } catch (final InterruptedException e) { }

        logger.info("Startup complete.");
    }

    public void shutdown() {
        if (!shutdownCalled) {
            logger.info(String.format("Shutting down server of type \"%s\"...", this.getClass().getSimpleName()));
        }

        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();

        for (final ChannelFuture future : channels) {
            try {
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) { }
        }

        for (final ServerConnector connector : connectors) {
            connector.close();
        }

        if (!shutdownCalled) {
            shutdownCalled = true;
            logger.info("Shutdown complete.");
        }
    }
}
