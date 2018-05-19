package com.gudderi.core.component.mybatis;

import com.gudderi.core.enums.Convertible;
import com.gudderi.core.enums.Prefecture;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrefectureTypeHandler extends BaseTypeHandler<Prefecture> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Prefecture parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public Prefecture getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        }
        return Convertible.findByCode(Prefecture.class, cs.getInt(columnIndex));
    }

    @Override
    public Prefecture getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return Convertible.findByCode(Prefecture.class, rs.getInt(columnName));
    }

    @Override
    public Prefecture getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return Convertible.findByCode(Prefecture.class, rs.getInt(columnIndex));
    }
}
