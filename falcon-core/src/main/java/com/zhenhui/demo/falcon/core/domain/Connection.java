package com.zhenhui.demo.falcon.core.domain;

import com.zhenhui.demo.falcon.core.support.utils.ChannelAttribute;
import com.zhenhui.demo.falcon.core.support.utils.ChannelAttributesUtils;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Connection {

    private final Channel channel;

    private Protocol protocol;

    public UniqueID deviceId() {
        return (UniqueID) ChannelAttributesUtils.get(channel, ChannelAttribute.DEVICE_ID);
    }

    public void sendCommand(Command command) {
        protocol.sendCommand(this, command);
    }

    public void write(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Connection that = (Connection)o;
        return channel.id().equals(that.channel.id());
    }

    @Override
    public int hashCode() {
        return this.channel.hashCode();
    }
}


