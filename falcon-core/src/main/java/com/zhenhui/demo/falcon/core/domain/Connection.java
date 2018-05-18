package com.zhenhui.demo.falcon.core.domain;

import io.netty.channel.Channel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Connection {
    @NonNull
    private final Channel channel;

    private UniqueID deviceId;

    private Protocol protocol;

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
        return this.channel.id().equals(that.channel.id());
    }

    @Override
    public int hashCode() {
        return this.channel.hashCode();
    }
}


