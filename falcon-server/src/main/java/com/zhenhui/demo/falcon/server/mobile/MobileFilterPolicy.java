package com.zhenhui.demo.falcon.server.mobile;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.support.Context;
import com.zhenhui.demo.falcon.core.support.handler.filter.AbstractFilterPolicy;

public class MobileFilterPolicy extends AbstractFilterPolicy {

    public MobileFilterPolicy(Context context) {
        super(context);
    }

    @Override
    public boolean accept(Position currPos, Position lastPos) {
        // TODO:

        return true;
    }

}
