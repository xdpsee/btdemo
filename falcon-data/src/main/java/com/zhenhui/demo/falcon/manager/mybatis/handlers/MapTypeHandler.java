package com.zhenhui.demo.falcon.manager.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.falcon.core.support.utils.JSONUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class MapTypeHandler extends BaseTypeHandler<Map<String, Object>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setString(i, JSONUtils.toJsonString(parameter));
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return JSONUtils.fromJsonString(value, new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return JSONUtils.fromJsonString(value, new TypeReference<Map<String, Object>>() {});

    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return JSONUtils.fromJsonString(value, new TypeReference<Map<String, Object>>() {});

    }
}
