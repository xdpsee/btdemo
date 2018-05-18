package com.zhenhui.demo.falcon.core.support.handler.filter;

import com.zhenhui.demo.falcon.core.domain.Position;

public interface FilterPolicy {

    boolean accept(Position currPos, Position lastPos);

}
