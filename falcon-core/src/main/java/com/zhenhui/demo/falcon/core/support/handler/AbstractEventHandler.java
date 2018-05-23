package com.zhenhui.demo.falcon.core.support.handler;

import java.util.Collection;

import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.server.ServerConnector;

public abstract class AbstractEventHandler extends AbstractDataHandler {

    public AbstractEventHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected Position handlePosition(Position position) {

        Collection<Event> events = analyzePosition(position);
        if (events != null) {
            for (Event event : events) {
                eventService().saveEvent(event);
            }
        }
        
        return position;
    }

    protected abstract Collection<Event> analyzePosition(Position position);

}
