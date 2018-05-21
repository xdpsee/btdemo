package com.zhenhui.demo.falcon.service.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhenhui.demo.falcon.core.domain.UniqueID;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class UniqueIDTypeHandler extends BaseTypeHandler<UniqueID> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UniqueID parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UniqueID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return UniqueID.fromString(value);
    }

    @Override
    public UniqueID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return UniqueID.fromString(value);
    }

    @Override
    public UniqueID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return UniqueID.fromString(value);
    }
}
