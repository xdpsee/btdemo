package com.zhenhui.demo.falcon.core.surport.handler.filter;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.surport.Context;
import com.zhenhui.demo.falcon.core.surport.handler.AbstractDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultDataFilter extends AbstractDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataFilter.class);


    private final FilterPolicy filterPolicy;

    public DefaultDataFilter(FilterPolicy filterPolicy) {
        this.filterPolicy = filterPolicy;
    }

    @Override
    protected Position handlePosition(Position position) {

        if (position != null) {
            Position lastPos = Context.positionService().getLastPosition(position.getDeviceId());

            try {
                if (filterPolicy != null && filterPolicy.filter(position, lastPos)) {
                    return null;
                }
            } catch (Exception e) {
                logger.error("filterPolicy.filter exception", e);
            }

        }

        return position;
    }

}
