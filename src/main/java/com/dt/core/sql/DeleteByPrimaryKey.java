package com.dt.core.sql;

import java.util.Collection;

/**
 * 删除
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface DeleteByPrimaryKey<T> extends Sql {

    T deleteByPrimaryKey(Object keyValue);

    T batchDeleteByPrimaryKeys(Object[] keyValues);

    T batchDeleteByPrimaryKeys(Collection<?> keyValues);

}
