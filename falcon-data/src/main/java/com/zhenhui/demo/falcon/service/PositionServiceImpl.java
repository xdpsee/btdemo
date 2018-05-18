package com.zhenhui.demo.falcon.service;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.service.PositionService;
import org.springframework.stereotype.Component;

@Component("positionServiceImpl")
public class PositionServiceImpl implements PositionService {

    @Override
    public void savePosition(Position position) {

    }

    @Override
    public Position getLastPosition(UniqueID deviceId) {
        return null;
    }

    @Override
    public boolean isLastPosition(Position position) {
        return false;
    }
}







