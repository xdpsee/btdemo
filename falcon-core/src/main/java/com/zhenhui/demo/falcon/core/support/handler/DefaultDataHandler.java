package com.zhenhui.demo.falcon.core.support.handler;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.server.ServerConnector;

public class DefaultDataHandler extends AbstractDataHandler {

    public DefaultDataHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected Position handlePosition(Position position) {

        try {
            if (position != null) {
                positionService().savePosition(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return position;
    }

}
