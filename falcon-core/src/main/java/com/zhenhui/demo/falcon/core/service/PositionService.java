package com.zhenhui.demo.falcon.core.service;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;

public interface PositionService {

    void savePosition(Position position);

    Position getLastPosition(UniqueID deviceId);

    boolean isLastPosition(Position position);

}
