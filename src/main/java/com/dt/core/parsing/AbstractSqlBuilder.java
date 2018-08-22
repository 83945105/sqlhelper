package com.dt.core.parsing;

import com.dt.core.sql.SqlSplicer;

/**
 * @author 白超
 * @date 2018/8/20
 */
public abstract class AbstractSqlBuilder implements SqlBuilder {

    protected SqlSplicer sqlSplicer = new SqlSplicer(128);

}
