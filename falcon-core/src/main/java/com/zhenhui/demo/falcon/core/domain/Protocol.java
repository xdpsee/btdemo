package com.zhenhui.demo.falcon.core.domain;

import java.util.Collection;

public interface Protocol {

    String getName();

    Collection<CommandType> getSupportedCommands();

    void sendCommand(Connection connection, Command command);

}
