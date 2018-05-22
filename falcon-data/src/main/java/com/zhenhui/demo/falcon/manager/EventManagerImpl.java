package com.zhenhui.demo.falcon.manager;

import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.manager.EventManager;
import com.zhenhui.demo.falcon.manager.dao.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("eventManagerImpl")
public class EventManagerImpl implements EventManager {

    @Autowired
    private EventDAO eventDAO;

    @Override
    public void saveEvent(Event event) {
        eventDAO.createEvent(event);
    }

}









