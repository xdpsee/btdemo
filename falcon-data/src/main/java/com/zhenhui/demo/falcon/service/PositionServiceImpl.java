package com.zhenhui.demo.falcon.service;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.service.PositionService;
import com.zhenhui.demo.falcon.service.dao.PositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("positionServiceImpl")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDAO positionDAO;

    @Override
    public void savePosition(Position position) {
        positionDAO.createPosition(position);
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






