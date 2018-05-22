package com.zhenhui.demo.falcon.core.support.handler;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Message;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.manager.DeviceManager;
import com.zhenhui.demo.falcon.core.manager.EventManager;
import com.zhenhui.demo.falcon.core.manager.PositionManager;
import com.zhenhui.demo.falcon.core.support.Context;
import com.zhenhui.demo.falcon.core.support.exception.DeviceNotFoundException;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttribute;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttributesUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHandler<T extends Message> extends SimpleChannelInboundHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    protected final Context context;

    public AbstractHandler(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

        UniqueID deviceId = msg.deviceId();
        Device device = deviceService().getDevice(deviceId);
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

    protected final DeviceManager deviceService() {
        return context.getDeviceManager();
    }

    protected final EventManager eventService() {
        return context.getEventManager();
    }

    protected final PositionManager positionService() {
        return context.getPositionManager();
    }

    protected final Configs configs() {
        return context.getConfigs();
    }
}

