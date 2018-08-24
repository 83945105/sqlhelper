package com.dt.core.sql;

/**
 * 删除
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Delete<T> extends Sql {

    T delete();

}
