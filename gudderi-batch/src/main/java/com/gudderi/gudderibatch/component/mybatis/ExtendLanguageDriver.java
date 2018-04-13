package com.gudderi.gudderibatch.component.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.Date;

public class ExtendLanguageDriver extends XMLLanguageDriver {
    private static final String ADDITIONAL_PARAM_NOW = "_now";

    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {
        return wrap(super.createSqlSource(configuration, script, parameterType));
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        return wrap(super.createSqlSource(configuration, script, parameterType));
    }

    /**
     * SqlSourceをラップして暗黙のパラメタを付与す機能を付加します。
     *
     * @param sqlSource SqlSource
     * @return SqlSource
     */
    private SqlSource wrap(SqlSource sqlSource) {
        return new SqlSource() {
            @Override
            public BoundSql getBoundSql(Object parameterObject) {
                BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
                boundSql.setAdditionalParameter(ADDITIONAL_PARAM_NOW, new Date());
                return boundSql;
            }
        };
    }
}
