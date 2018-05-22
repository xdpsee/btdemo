package com.zhenhui.demo.falcon.manager.mybatis.mapper;

import java.util.Date;
import java.util.Map;

import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceMapper {

    Device selectById(@Param("deviceId") UniqueID deviceId);

    int insert(Device device);

    int updateAttributes(@Param("deviceId") UniqueID deviceId
        , @Param("attributes") Map<String, Object> attributes
        , @Param("lastGmtUpdate") Date date);
}



