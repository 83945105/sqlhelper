package com.dt.core.sql;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface QueryByPrimaryKey<T> extends Sql {

    T queryByPrimaryKey(Object keyValue);

}
