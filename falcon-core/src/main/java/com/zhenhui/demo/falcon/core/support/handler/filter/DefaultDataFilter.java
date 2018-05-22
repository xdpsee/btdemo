package com.zhenhui.demo.falcon.core.support.handler.filter;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.support.Context;
import com.zhenhui.demo.falcon.core.support.handler.AbstractDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultDataFilter extends AbstractDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataFilter.class);

    private final FilterPolicy filterPolicy;

    public DefaultDataFilter(Context context, FilterPolicy filterPolicy) {
        super(context);
        this.filterPolicy = filterPolicy;
    }

    @Override
    protected Position handlePosition(Position position) {

        if (position != null) {
            Position lastPos = context.getPositionManager().getLastPosition(position.getDeviceId());

            try {
                if (filterPolicy != null && !filterPolicy.accept(position, lastPos)) {
                    return null;
                }
            } catch (Exception e) {
                logger.error("filterPolicy.accept exception", e);
            }

        }

        return position;
    }

}
