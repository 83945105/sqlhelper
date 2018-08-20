package com.dt.core.sql;

import com.dt.core.engine.ColumnEngine;
import com.dt.core.norm.Model;

import java.util.Collection;
import java.util.Map;

/**
 * 插入引擎
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Insert extends Sql {

    int insertArgs(Object[] args, ColumnEngine columnEngine);

    int insertArgs(Collection<?> args, ColumnEngine columnEngine);

    <T extends Model> int insertRecord(Map<String, ?> record, Class<T> modelClass);

    <T extends Model> int insertRecord(Map<String, ?> record, String tableName, Class<T> modelClass);

    <T extends Model> int insertRecord(Object record, Class<T> modelClass);

    <T extends Model> int insertRecord(Object record, String tableName, Class<T> modelClass);

    int insertRecord(Map<String, ?> record, ColumnEngine columnEngine);

    int insertRecord(Object record, ColumnEngine columnEngine);

    <T extends Model> int insertRecordSelective(Map<String, ?> record, Class<T> modelClass);

    <T extends Model> int insertRecordSelective(Map<String, ?> record, String tableName, Class<T> modelClass);

    <T extends Model> int insertRecordSelective(Object record, Class<T> modelClass);

    <T extends Model> int insertRecordSelective(Object record, String tableName, Class<T> modelClass);

    int insertRecordSelective(Map<String, ?> record, ColumnEngine columnEngine);

    int insertRecordSelective(Object record, ColumnEngine columnEngine);

    <T extends Model> int batchInsertRecords(Object[] records, Class<T> modelClass);

    <T extends Model> int batchInsertRecords(Object[] records, String tableName, Class<T> modelClass);

    <T extends Model> int batchInsertRecords(Collection<?> records, Class<T> modelClass);

    <T extends Model> int batchInsertRecords(Collection<?> records, String tableName, Class<T> modelClass);

    int batchInsertRecords(Object[] records, ColumnEngine columnEngine);

    int batchInsertRecords(Collection<?> records, ColumnEngine columnEngine);

}
