package com.zhenhui.demo.falcon.service.mybatis.mapper;

import com.zhenhui.demo.falcon.core.domain.Event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {

    int insert(Event event);

}
