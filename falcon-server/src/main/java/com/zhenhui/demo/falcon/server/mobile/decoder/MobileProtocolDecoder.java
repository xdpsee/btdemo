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
import com.zhenhui.demo.falcon.server.mobile.message.RegistryMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

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
            .expression("([NS])")
            .number("(dd)(dd.d+)")               // latitude
            .expression("([WE])")
            .number("(ddd)(dd.d+),")             // longitude
            .number("(d+.d+),")                  // altitude
            .number("(d+.d+),")                  // speed
            .expression("(\\d{14})#").compile();

    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(()->{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format;
    });

    /**
     * ##1,{protocol_version},{imei},{app_version},{gmt_time}#
     * ##2,{protocol_version},{imei},{N2240.8887E11359.2994},{alt},{speed},{gmt_time}#
     * ##3,{protocol_version},{imei},{gmt_time}#
     */
    @Override
    public Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws DecodeException {

        if (msg instanceof ByteBuf) {

            byte[] bytes = new byte[((ByteBuf) msg).readableBytes()];
            ((ByteBuf) msg).getBytes(0, bytes);
            String message = new String(bytes);

            return handle(channel, message);

        }

        return null;
    }

    private Message handle(Channel channel, String message) throws DecodeException {

        if (message.startsWith("##1")) {
            Parser parser = new Parser(PATTERN_LOGIN, message);
            if (parser.matches()) {
                parser.nextInt(); // skip cmd
                return new RegistryMessage(parser.nextDouble()
                        , parser.next()
                        , parser.nextDouble()
                        , parser.next()
                        , message);
            }
        }

        if (message.startsWith("##2")) {
            Parser parser = new Parser(PATTERN_POSITION, message);
            if (parser.matches()) {
                parser.nextInt(); // skip cmd

                UniqueID deviceId = (UniqueID) ChannelAttributesUtils.get(channel, ChannelAttribute.DEVICE_ID);
                if (null == deviceId) {
                    throw new DecodeException("device is not login.");
                }

                Position position = new Position();
                position.setDeviceId(deviceId);
                position.setLocated(true);

                parser.nextDouble(); // skip ProtocolVersion
                parser.next(); // skip UniqueId
                double latitude = parser.nextCoordinate(Parser.CoordinateFormat.HEM_DEG_MIN);
                double longitude = parser.nextCoordinate(Parser.CoordinateFormat.HEM_DEG_MIN);
                position.setLatitude(latitude);
                position.setLongitude(longitude);
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
        }

        if (message.startsWith("##3")) {

        }

        return null;
    }



}


