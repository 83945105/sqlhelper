package com.dt.core.sql;

/**
 * 表引擎
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Table extends Sql {

    int copyTable(String sourceTableName, String targetTableName);

    int deleteTable(String tableName);

    int renameTable(String sourceTableName, String targetTableName);

    boolean isTableExist(String tableName);

}
