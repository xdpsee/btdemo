package com.zhenhui.demo.falcon.core.surport.handler;

import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Message;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.surport.Context;
import com.zhenhui.demo.falcon.core.surport.exception.DeviceNotFoundException;
import com.zhenhui.demo.falcon.core.surport.utils.ChannelAttribute;
import com.zhenhui.demo.falcon.core.surport.utils.ChannelAttributesUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHandler<T extends Message> extends SimpleChannelInboundHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

        UniqueID deviceId = msg.deviceId();
        Device device = Context.deviceService().queryDevice(deviceId);
        if (null == device) {
            throw new DeviceNotFoundException(String.format("device: %s not found", deviceId));
        }

        ChannelAttributesUtils.setIfAbsent(ctx, ChannelAttribute.DEVICE_ID, device.getId());

        messageReceived(ctx, msg);
    }

    protected abstract void messageReceived(ChannelHandlerContext ctx, T msg) throws Exception;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        try {
            ctx.close();
        } finally {
            logger.error("", cause);
        }
    }
}

