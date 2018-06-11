package com.zhenhui.demo.falcon.service;

import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.support.service.EventService;
import com.zhenhui.demo.falcon.service.dao.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("eventServiceImpl")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Override
    public void saveEvent(Event event) {
        eventDAO.createEvent(event);
    }

}









