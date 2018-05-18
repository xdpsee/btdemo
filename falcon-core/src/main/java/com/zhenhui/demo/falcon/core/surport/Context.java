package com.zhenhui.demo.falcon.core.surport;

import com.zhenhui.demo.falcon.core.service.DeviceService;
import com.zhenhui.demo.falcon.core.service.EventService;
import com.zhenhui.demo.falcon.core.service.PositionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Context implements ApplicationContextAware {

    private static volatile ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Context.applicationContext = applicationContext;
    }

    public static DeviceService deviceService() {
        return getBean(DeviceService.class);
    }

    public static PositionService positionService() {
        return getBean(PositionService.class);
    }

    public static EventService eventService() {
        return getBean(EventService.class);
    }

    private static <T> T getBean(Class<T> clazz) {
        return Context.applicationContext.getBean(clazz);
    }

}
