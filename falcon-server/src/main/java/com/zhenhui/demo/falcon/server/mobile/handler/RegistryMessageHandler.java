package com.zhenhui.demo.falcon.server.mobile.handler;

import com.zhenhui.demo.falcon.core.support.Context;
import com.zhenhui.demo.falcon.core.support.handler.AbstractHandler;
import com.zhenhui.demo.falcon.server.mobile.message.RegistryMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistryMessageHandler extends AbstractHandler<RegistryMessage> {

    private static final Logger logger = LoggerFactory.getLogger(RegistryMessage.class);

    public RegistryMessageHandler(Context context) {
        super(context);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, RegistryMessage msg) throws Exception {

        logger.info("RegistryMessageHandler.messageReceived: {}", msg);

    }
}


