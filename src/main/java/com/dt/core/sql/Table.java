package com.dt.core.sql;

/**
 * 表
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Table<T> extends Sql {

    T copyTable(String targetTableName);

    T deleteTable();

    T renameTable(String newTableName);

    T isTableExist();

}
