package com.zhenhui.demo.falcon.core.support.handler;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDataHandler extends AbstractDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataHandler.class);

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
            logger.error("DefaultDataHandler.savePosition exception", e);
            throw e;
        }

        return position;
    }

}
