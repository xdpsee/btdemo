package com.zhenhui.demo.falcon.core.surport.decoder;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.surport.exception.DecodeException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public abstract class AbstractProtocolDecoder extends MessageToMessageDecoder<Object> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {

        Object result = decode(ctx.channel(), ctx.channel().remoteAddress(), msg);
        if (msg == result) {
            ctx.fireChannelRead(msg);
        } else {
            if (null != result) {
                if (result instanceof Collection) {
                    for (Object obj : (Collection) result) {
                        saveOrigin(obj, msg);
                        out.add(obj);
                    }
                } else {
                    saveOrigin(result, msg);
                    out.add(result);
                }
            }
        }
    }

    public abstract Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws DecodeException;

    private void saveOrigin(Object decodedMessage, Object originalMessage) {

        if (decodedMessage instanceof Position) {
            Position position = (Position) decodedMessage;
            if (originalMessage instanceof ByteBuf) {
                ByteBuf buf = (ByteBuf) originalMessage;
                position.set(Position.KEY_ORIGINAL, ByteBufUtil.hexDump(buf, 0, buf.writerIndex()));
            } else if (originalMessage instanceof String) {
                position.set(Position.KEY_ORIGINAL, DatatypeConverter.printHexBinary(
                        ((String) originalMessage).getBytes(StandardCharsets.UTF_8)));
            }

        }
    }
}




