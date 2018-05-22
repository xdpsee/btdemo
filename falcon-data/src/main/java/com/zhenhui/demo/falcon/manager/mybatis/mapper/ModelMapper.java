package com.zhenhui.demo.falcon.manager.mybatis.mapper;

import com.zhenhui.demo.falcon.core.domain.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ModelMapper {

    Model selectByName(@Param("name") String model);

    int insert(Model model);
}




