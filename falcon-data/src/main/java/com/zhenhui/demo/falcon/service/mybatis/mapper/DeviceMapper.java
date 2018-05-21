package com.zhenhui.demo.falcon.service.mybatis.mapper;

import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceMapper {

    Device selectById(@Param("deviceId") UniqueID deviceId);

}

