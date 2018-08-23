package com.dt.core.build;

import com.dt.core.sql.Query;
import com.dt.core.sql.Table;

import java.util.List;

/**
 * SQL构建器
 *
 * @author 白超
 * @date 2018/8/15
 */
public interface SqlBuilder extends Table<SqlBuilder>, Query<SqlBuilder> {

    String getPreparedStatementSql();

    List<Object> getPreparedStatementArgs();

}
