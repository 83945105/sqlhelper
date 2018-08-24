package com.dt.core.build;

import com.dt.core.sql.*;

import java.util.List;

/**
 * SQL构建器
 *
 * @author 白超
 * @date 2018/8/15
 */
public interface SqlBuilder extends Table<SqlBuilder>, QueryByPrimaryKey<SqlBuilder>, Query<SqlBuilder>, Insert<SqlBuilder>, UpdateByPrimaryKey<SqlBuilder>, Update<SqlBuilder>, DeleteByPrimaryKey<SqlBuilder>, Delete<SqlBuilder> {

    String getPreparedStatementSql();

    List<Object> getPreparedStatementArgs();

}
