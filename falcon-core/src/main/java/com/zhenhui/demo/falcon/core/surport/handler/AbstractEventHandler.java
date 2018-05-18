package com.zhenhui.demo.falcon.core.surport.handler;

import java.util.Collection;

import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.surport.Context;

public abstract class AbstractEventHandler extends AbstractDataHandler {


    @Override
    protected Position handlePosition(Position position) {

        Collection<Event> events = analyzePosition(position);
        if (events != null) {
            for (Event event : events) {
                Context.eventService().saveEvent(event);
            }
        }

        return position;
    }

    protected abstract Collection<Event> analyzePosition(Position position);


}
