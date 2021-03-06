package com.zhenhui.demo.falcon.service.dao;

import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.service.mybatis.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class PositionDAO {

    @Autowired
    private PositionMapper positionMapper;

    public void createPosition(Position position) {
        positionMapper.insert(position);
    }

}

