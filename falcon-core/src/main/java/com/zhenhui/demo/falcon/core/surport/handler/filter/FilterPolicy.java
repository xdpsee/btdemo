package com.zhenhui.demo.falcon.core.surport.handler.filter;

import com.zhenhui.demo.falcon.core.domain.Position;

public interface FilterPolicy {

    boolean filter(Position currPos, Position lastPos);

}
