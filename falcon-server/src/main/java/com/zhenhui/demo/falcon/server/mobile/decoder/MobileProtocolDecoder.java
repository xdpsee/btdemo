package com.zhenhui.demo.falcon.server.mobile.decoder;

import java.net.SocketAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import com.zhenhui.demo.falcon.core.domain.Message;
import com.zhenhui.demo.falcon.core.domain.Network;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.decoder.AbstractProtocolDecoder;
import com.zhenhui.demo.falcon.core.support.exception.DecodeException;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttribute;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttributesUtils;
import com.zhenhui.demo.falcon.core.support.utils.Parser;
import com.zhenhui.demo.falcon.core.support.utils.PatternBuilder;
import com.zhenhui.demo.falcon.server.common.IdGenerator;
import com.zhenhui.demo.falcon.server.mobile.message.LoginMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class MobileProtocolDecoder extends AbstractProtocolDecoder {

    public static final Pattern PATTERN_LOGIN = new PatternBuilder()
        .text("##")
        .number("(d{1}),")
        .number("(d+.d+),")
        .expression("(\\d{15}),")
        .number("(d+.d+),")
        .expression("(\\d{14})#").compile();

    public static final Pattern PATTERN_POSITION = new PatternBuilder()
        .text("##")
        .number("(d{1}),")
        .number("(d+.d+),")                 // protocol_version
        .expression("([0-9A-Za-z]{15}),")   // IMEI
        .number("(d+.d+),")                 // latitude
        .number("(d+.d+),")                 // longitude
        .number("(d+.d+),")                 // altitude
        .number("(d+.d+),")                 // speed
        .expression("(\\d{14})#").compile();

    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format;
    });

    @Override
    public Object decode(ChannelHandlerContext ctx, SocketAddress remoteAddress, Object msg) throws DecodeException {

        if (msg instanceof ByteBuf) {

            byte[] bytes = new byte[((ByteBuf)msg).readableBytes()];
            ((ByteBuf)msg).getBytes(0, bytes);
            String message = new String(bytes);

            return handle(ctx, message);

        }

        return msg;
    }

    /**
     * ##1,{protocol_version},{imei},{app_version},{gmt_time}#
     * ##2,{protocol_version},{imei},{lat},{lng},{alt},{speed},{gmt_time}#
     * ##3,{protocol_version},{imei},{gmt_time}#
     */
    private Message handle(ChannelHandlerContext ctx, String message) throws DecodeException {

        if (message.startsWith("##1")) {
            return parseRegistryMessage(message);
        }

        if (message.startsWith("##2")) {
            return parsePosition(ctx, message);
        }

        if (message.startsWith("##3")) {

        }

        return null;
    }

    private Message parseRegistryMessage(String message) {
        Parser parser = new Parser(PATTERN_LOGIN, message);
        if (parser.matches()) {
            parser.nextInt(); // skip cmd
            return new LoginMessage(parser.nextDouble()
                , parser.next()
                , parser.nextDouble()
                , parser.next()
                , message);
        }

        return null;
    }

    private Position parsePosition(ChannelHandlerContext ctx, String message) throws DecodeException {
        Parser parser = new Parser(PATTERN_POSITION, message);
        if (parser.matches()) {
            parser.nextInt(); // skip cmd

            UniqueID deviceId = (UniqueID)ChannelAttributesUtils.get(ctx, ChannelAttribute.DEVICE_ID);
            if (null == deviceId) {
                throw new DecodeException("device is not login.");
            }

            Position position = new Position();
            position.setId(IdGenerator.next());
            position.setDeviceId(deviceId);
            position.setLocated(true);

            parser.nextDouble(); // skip ProtocolVersion
            parser.next(); // skip UniqueId
            position.setLatitude(parser.nextDouble());
            position.setLongitude(parser.nextDouble());
            double altitude = parser.nextDouble();
            position.setAltitude(altitude);
            double speed = parser.nextDouble();
            position.setSpeed(speed);
            String timeStr = parser.next();
            try {
                Date date = DATE_FORMAT.get().parse(timeStr);
                position.setTime(date);
            } catch (ParseException e) {
                throw new DecodeException(e);
            }

            position.setNetwork(new Network());

            return position;
        }

        return null;
    }

}


