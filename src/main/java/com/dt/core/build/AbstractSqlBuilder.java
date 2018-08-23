package com.dt.core.build;

import com.dt.core.sql.SqlSplicer;

import java.util.*;

/**
 * @author 白超
 * @date 2018/8/20
 */
public abstract class AbstractSqlBuilder implements SqlBuilder {

    protected SqlSplicer sqlSplicer = new SqlSplicer(128);

    protected List<Object> sqlArgs;

    @Override
    public String getPreparedStatementSql() {
        return this.sqlSplicer.getSql();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        return this.sqlArgs;
    }

}
