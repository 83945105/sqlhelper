package com.dt.core.sql;

import com.dt.core.engine.ColumnEngine;
import com.dt.core.engine.WhereEngine;
import com.dt.core.norm.Model;

import java.util.Collection;
import java.util.Map;

/**
 * 更新引擎
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Update extends Sql {

    int updateArgsByPrimaryKey(Object keyValue, Object[] args, ColumnEngine columnEngine);

    int updateArgsByPrimaryKey(Object keyValue, Collection<?> args, ColumnEngine columnEngine);

    <T extends Model> int updateRecordByPrimaryKey(Object keyValue, Map<String, ?> record, Class<T> modelClass);

    <T extends Model> int updateRecordByPrimaryKey(Object keyValue, Object record, Class<T> modelClass);

    <T extends Model> int updateRecordByPrimaryKey(Object keyValue, Map<String, ?> record, String tableName, Class<T> modelClass);

    <T extends Model> int updateRecordByPrimaryKey(Object keyValue, Object record, String tableName, Class<T> modelClass);

    int updateRecordByPrimaryKey(Object keyValue, Map<String, ?> record, ColumnEngine columnEngine);

    int updateRecordByPrimaryKey(Object keyValue, Object record, ColumnEngine columnEngine);

    <T extends Model> int updateRecordByPrimaryKeySelective(Object keyValue, Map<String, ?> record, Class<T> modelClass);

    <T extends Model> int updateRecordByPrimaryKeySelective(Object keyValue, Map<String, ?> record, String tableName, Class<T> modelClass);

    <T extends Model> int updateRecordByPrimaryKeySelective(Object keyValue, Object record, Class<T> modelClass);

    <T extends Model> int updateRecordByPrimaryKeySelective(Object keyValue, Object record, String tableName, Class<T> modelClass);

    int updateRecordByPrimaryKeySelective(Object keyValue, Map<String, ?> record, ColumnEngine columnEngine);

    int updateRecordByPrimaryKeySelective(Object keyValue, Object record, ColumnEngine columnEngine);

    int updateRecord(Map<String, ?> record, WhereEngine whereEngine);

    int updateRecord(Object record, WhereEngine whereEngine);

    int updateRecordSelective(Map<String, ?> record, WhereEngine whereEngine);

    int updateRecordSelective(Object record, WhereEngine whereEngine);

    <T extends Model> int batchUpdateRecordsByPrimaryKeys(Object[] records, Class<T> modelClass);

    <T extends Model> int batchUpdateRecordsByPrimaryKeys(Object[] records, String tableName, Class<T> modelClass);

    <T extends Model> int batchUpdateRecordsByPrimaryKeys(Collection<?> records, Class<T> modelClass);

    <T extends Model> int batchUpdateRecordsByPrimaryKeys(Collection<?> records, String tableName, Class<T> modelClass);

    int batchUpdateRecordsByPrimaryKeys(Object[] records, WhereEngine whereEngine);

    int batchUpdateRecordsByPrimaryKeys(Collection<?> records, WhereEngine whereEngine);

    int updateOrInsertArgs(Object[] batchArgs, ColumnEngine columnEngine);

    int updateOrInsertArgs(Collection<?> batchArgs, ColumnEngine columnEngine);

    <T extends Model> int updateOrInsertRecord(Object[] records, Class<T> modelClass);

    <T extends Model> int updateOrInsertRecord(Object[] records, String tableName, Class<T> modelClass);

    <T extends Model> int updateOrInsertRecord(Collection<?> records, Class<T> modelClass);

    <T extends Model> int updateOrInsertRecord(Collection<?> records, String tableName, Class<T> modelClass);

    int updateOrInsertRecord(Object[] records, ColumnEngine columnEngine);

    int updateOrInsertRecord(Collection<?> records, ColumnEngine columnEngine);
}
