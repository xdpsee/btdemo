package com.zhenhui.demo.falcon.service.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.demo.falcon.core.domain.Attributes;
import com.zhenhui.demo.falcon.core.support.utils.JSONUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class AttributesTypeHandler extends BaseTypeHandler<Attributes> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Attributes parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setString(i, JSONUtils.toJsonString(parameter));
    }

    @Override
    public Attributes getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return JSONUtils.fromJsonString(value, new TypeReference<Attributes>() {});
    }

    @Override
    public Attributes getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return JSONUtils.fromJsonString(value, new TypeReference<Attributes>() {});
    }

    @Override
    public Attributes getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return JSONUtils.fromJsonString(value, new TypeReference<Attributes>() {});
    }
}
