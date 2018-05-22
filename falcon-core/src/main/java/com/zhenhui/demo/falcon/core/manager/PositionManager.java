package com.zhenhui.demo.falcon.core.manager;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;

public interface PositionManager {

    void savePosition(Position position);

    Position getLastPosition(UniqueID deviceId);

    boolean isLastPosition(Position position);

}
