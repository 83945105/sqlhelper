package com.dt.core.sql;

import com.dt.core.engine.WhereEngine;
import com.dt.core.norm.Model;

import java.util.Collection;

/**
 * 删除引擎
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Delete extends Sql {

    <T extends Model> int deleteByPrimaryKey(Object keyValue, Class<T> modelClass);

    <T extends Model> int deleteByPrimaryKey(Object keyValue, String tableName, Class<T> modelClass);

    <T extends Model> int batchDeleteByPrimaryKeys(Object[] keyValues, Class<T> modelClass);

    <T extends Model> int batchDeleteByPrimaryKeys(Object[] keyValues, String tableName, Class<T> modelClass);

    <T extends Model> int batchDeleteByPrimaryKeys(Collection<?> keyValues, Class<T> modelClass);

    <T extends Model> int batchDeleteByPrimaryKeys(Collection<?> keyValues, String tableName, Class<T> modelClass);

    int delete(WhereEngine whereEngine);

}
