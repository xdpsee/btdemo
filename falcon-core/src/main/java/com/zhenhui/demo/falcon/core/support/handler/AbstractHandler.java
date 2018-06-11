package com.zhenhui.demo.falcon.core.support.handler;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Message;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.service.DeviceService;
import com.zhenhui.demo.falcon.core.support.service.EventService;
import com.zhenhui.demo.falcon.core.support.service.PositionService;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import com.zhenhui.demo.falcon.core.support.exception.DeviceNotFoundException;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttribute;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttributesUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHandler<T extends Message> extends SimpleChannelInboundHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    protected final ServerConnector connector;

    public AbstractHandler(ServerConnector connector) {
        super();
        this.connector = connector;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

        checkDevice(ctx, msg);

        Connection connection = (Connection)ChannelAttributesUtils.get(ctx.channel(), ChannelAttribute.CONNECTION);

        messageReceived(ctx, connection, msg);
    }

    protected abstract void messageReceived(ChannelHandlerContext ctx, Connection connection, T msg) throws Exception;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        final Channel channel = ctx.channel();
        if (cause instanceof ClosedChannelException) {
            logger.warn("AbstractHandler.exceptionCaught, attempt to write to closed channel " + channel);
        } else {
            if (cause instanceof IOException && "Connection reset by peer".equals(cause.getMessage())) {
                logger.warn("AbstractHandler.exceptionCaught, connection reset by peer");
            } else {
                logger.error("AbstractHandler.exceptionCaught, unexpected exception from downstream for " + channel,
                    cause);
            }

            ctx.close();
        }
    }

    protected final DeviceService deviceService() {
        return connector.getContext().getDeviceService();
    }

    protected final EventService eventService() {
        return connector.getContext().getEventService();
    }

    protected final PositionService positionService() {
        return connector.getContext().getPositionService();
    }

    protected final Configs configs() {
        return connector.getConfigs();
    }

    private void checkDevice(ChannelHandlerContext ctx, T msg) throws Exception {
        UniqueID deviceId = (UniqueID)ChannelAttributesUtils.get(ctx, ChannelAttribute.DEVICE_ID);
        if (null == deviceId) {
            deviceId = msg.deviceId();
            if (deviceId != null) {
                Device device = deviceService().getDevice(deviceId);
                if (null == device) {
                    throw new DeviceNotFoundException(String.format("device: %s not found", deviceId));
                }

                ChannelAttributesUtils.setIfAbsent(ctx, ChannelAttribute.DEVICE_ID, deviceId);
            }
        }
    }
}

