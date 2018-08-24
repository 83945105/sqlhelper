package com.dt.core.sql;

import java.util.Collection;

/**
 * 更新
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface UpdateByPrimaryKey<T> extends Sql {

    T updateArgsByPrimaryKey(Object keyValue, Object[] args);

    T updateArgsByPrimaryKey(Object keyValue, Collection<?> args);

    T updateJavaBeanByPrimaryKey(Object keyValue, Object record);

    T updateJavaBeanByPrimaryKeySelective(Object keyValue, Object record);

    T batchUpdateJavaBeansByPrimaryKeys(Object[] records);

    T batchUpdateJavaBeansByPrimaryKeys(Collection<?> records);

}
