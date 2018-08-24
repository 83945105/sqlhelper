package com.dt.core.sql;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Query<T> extends Sql {

    T query();

    T queryCount();

}
