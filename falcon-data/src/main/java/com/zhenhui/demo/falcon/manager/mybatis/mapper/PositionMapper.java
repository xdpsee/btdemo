package com.zhenhui.demo.falcon.manager.mybatis.mapper;

import com.zhenhui.demo.falcon.core.domain.Position;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionMapper {

    int insert(Position position);

}

