package com.gudderi.core.component.mybatis;


import com.gudderi.core.enums.Convertible;
import com.gudderi.core.enums.InformationType;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO: Convertibleを実装したEnumクラスはまとめて動的にTypeHandlerインスタンスを作れるように改良する
 */
public class InformationTypeTypeHandler extends BaseTypeHandler<InformationType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InformationType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public InformationType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        }
        return Convertible.findByCode(InformationType.class, cs.getInt(columnIndex));
    }

    @Override
    public InformationType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return Convertible.findByCode(InformationType.class, rs.getInt(columnName));
    }

    @Override
    public InformationType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return Convertible.findByCode(InformationType.class, rs.getInt(columnIndex));
    }
}
