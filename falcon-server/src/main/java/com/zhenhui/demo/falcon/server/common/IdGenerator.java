package com.zhenhui.demo.falcon.server.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator implements ApplicationContextAware {

    private static com.imadcn.framework.idworker.generator.IdGenerator idGen;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IdGenerator.idGen = applicationContext.getBean(com.imadcn.framework.idworker.generator.IdGenerator.class);
    }

    public static long next() {
        return idGen.nextId();
    }
}



