package com.zhenhui.demo.falcon.manager;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.manager.PositionManager;
import com.zhenhui.demo.falcon.manager.dao.PositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("positionManagerImpl")
public class PositionManagerImpl implements PositionManager {

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






