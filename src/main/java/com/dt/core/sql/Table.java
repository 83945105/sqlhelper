package com.dt.core.sql;

/**
 * 表引擎
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Table<T> extends Sql {

    T copyTable(String sourceTableName, String targetTableName);

    T deleteTable(String tableName);

    T renameTable(String sourceTableName, String targetTableName);

    T isTableExist(String tableName);

}
