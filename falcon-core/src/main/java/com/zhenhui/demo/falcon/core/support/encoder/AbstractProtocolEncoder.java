package com.zhenhui.demo.falcon.core.support.encoder;

import com.zhenhui.demo.falcon.core.domain.Command;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.utils.ExceptionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractProtocolEncoder extends MessageToByteEncoder<Command> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractProtocolEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Command command, ByteBuf out) throws Exception {
        try {
            byte[] bytes = encodeCommand(ctx, command);

            StringBuilder s = new StringBuilder();
            s.append(String.format("[%s] ", ctx.channel().id()));
            s.append("ID: ").append(encodeDeviceId(command.getDeviceId())).append(", ");
            s.append("CMD: ").append(command.getType()).append(" ");
            if (bytes != null) {
                s.append("SENT");
            } else {
                s.append("NOT SENT");
            }

            logger.info(s.toString());

            out.writeBytes(bytes);
        } catch (Exception e) {
            logger.error("AbstractProtocolEncoder.encode exception: {}", ExceptionUtils.getStackTrace(e));
        }
    }

    private byte[] encodeCommand(ChannelHandlerContext ctx, Command command) throws Exception {
        return encodeCommand(command);
    }

    protected abstract byte[] encodeCommand(Command command) throws Exception;

    protected abstract String encodeDeviceId(UniqueID deviceId);
}






