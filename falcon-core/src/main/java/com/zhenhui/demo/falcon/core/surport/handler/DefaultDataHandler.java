package com.zhenhui.demo.falcon.core.surport.handler;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.surport.Context;

public class DefaultDataHandler extends AbstractDataHandler {


    @Override
    protected Position handlePosition(Position position) {

        try {
            Context.positionService().savePosition(position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return position;
    }

}
