package com.zhenhui.demo.falcon.server.mobile.handler;

import com.zhenhui.demo.falcon.core.domain.Command;
import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import com.zhenhui.demo.falcon.core.support.handler.AbstractHandler;
import com.zhenhui.demo.falcon.server.mobile.message.LoginMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginMessageHandler extends AbstractHandler<LoginMessage> {

    private static final Logger logger = LoggerFactory.getLogger(LoginMessage.class);

    public LoginMessageHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Connection connection, LoginMessage msg) throws Exception {

        logger.info("LoginMessageHandler.messageReceived: {}", msg);

        Command command = new Command();
        command.setType(CommandType.TYPE_CUSTOM);
        command.set(Command.KEY_DATA, "4F4B");

        connection.sendCommand(command);
    }

}




