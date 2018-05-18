package com.zhenhui.demo.falcon.core.support;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import com.zhenhui.demo.falcon.core.domain.Command;
import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Connection;
import com.zhenhui.demo.falcon.core.domain.Protocol;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.string.StringEncoder;

public abstract class AbstractProtocol implements Protocol {

    private final Context context;
    private final String name;

    protected AbstractProtocol(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public Collection<CommandType> getSupportedCommands() {
        Set<CommandType> commands = new HashSet<>();

        commands.add(CommandType.TYPE_CUSTOM);

        return commands;
    }

    @Override
    public void sendCommand(Connection connection, Command command) {
        Set<CommandType> supportedCommands = context.getDeviceService().getSupportedCommands(connection.getDeviceId());
        if (supportedCommands.contains(command.getType())) {
            connection.write(command);
        } else if (command.getType().equals(CommandType.TYPE_CUSTOM)) {
            String data = command.getString(Command.KEY_DATA);
            if (connection.getChannel().pipeline().get(StringEncoder.class) != null) {
                connection.write(data);
            } else {
                connection.write(Unpooled.wrappedBuffer(DatatypeConverter.parseHexBinary(data)));
            }
        } else {
            throw new RuntimeException("Command " + command.getType() + " is not supported in protocol " + getName());
        }
    }

}
