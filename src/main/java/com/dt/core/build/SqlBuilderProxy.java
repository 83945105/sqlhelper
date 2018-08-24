package com.dt.core.build;

import com.dt.core.data.SqlData;
import com.dt.core.norm.Model;
import com.dt.core.sql.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Sql构建代理器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class SqlBuilderProxy implements Table<SqlBuilder>, QueryByPrimaryKey<SqlBuilder>, Query<SqlBuilder>, Insert<SqlBuilder>, UpdateByPrimaryKey<SqlBuilder>, Update<SqlBuilder>, DeleteByPrimaryKey<SqlBuilder>, Delete<SqlBuilder> {

    @Autowired(required = false)
    private SqlBuilder sqlBuilder;

    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    public <M extends Model> SqlBuilderProxy(SqlData<M> sqlData) {
        if (this.sqlBuilder == null) {
            switch (sqlData.getDataBaseType()) {
                case MYSQL:
                    this.sqlBuilder = new MySqlBuilder<>(sqlData);
                    break;
                default:
            }
        }
    }

    @Override
    public SqlBuilder copyTable(String targetTableName) {
        return this.sqlBuilder.copyTable(targetTableName);
    }

    @Override
    public SqlBuilder deleteTable() {
        return this.sqlBuilder.deleteTable();
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        return this.sqlBuilder.renameTable(newTableName);
    }

    @Override
    public SqlBuilder isTableExist() {
        return this.sqlBuilder.isTableExist();
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilder.queryByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder query() {
        return this.sqlBuilder.query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilder.queryCount();
    }

    @Override
    public SqlBuilder insertArgs(Object[] args) {
        return this.sqlBuilder.insertArgs(args);
    }

    @Override
    public SqlBuilder insertArgs(Collection<?> args) {
        return this.sqlBuilder.insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object record) {
        return this.sqlBuilder.insertJavaBean(record);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object record) {
        return this.sqlBuilder.insertJavaBeanSelective(record);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Object[] records) {
        return this.sqlBuilder.batchInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> records) {
        return this.sqlBuilder.batchInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Object[] args) {
        return this.sqlBuilder.updateArgsByPrimaryKey(keyValue, args);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Collection<?> args) {
        return this.sqlBuilder.updateArgsByPrimaryKey(keyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object keyValue, Object record) {
        return this.sqlBuilder.updateJavaBeanByPrimaryKey(keyValue, record);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object keyValue, Object record) {
        return this.sqlBuilder.updateJavaBeanByPrimaryKeySelective(keyValue, record);
    }

    @Override
    public SqlBuilder updateJavaBean(Object record) {
        return this.sqlBuilder.updateJavaBean(record);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object record) {
        return this.sqlBuilder.updateJavaBeanSelective(record);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Object[] records) {
        return this.sqlBuilder.batchUpdateJavaBeansByPrimaryKeys(records);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> records) {
        return this.sqlBuilder.batchUpdateJavaBeansByPrimaryKeys(records);
    }

    @Override
    public SqlBuilder updateOrInsertArgs(Object[] batchArgs) {
        return this.sqlBuilder.updateOrInsertArgs(batchArgs);
    }

    @Override
    public SqlBuilder updateOrInsertArgs(Collection<?> batchArgs) {
        return this.sqlBuilder.updateOrInsertArgs(batchArgs);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Object[] records) {
        return this.sqlBuilder.updateOrInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> records) {
        return this.sqlBuilder.updateOrInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object keyValue) {
        return this.sqlBuilder.deleteByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object[] keyValues) {
        return this.sqlBuilder.batchDeleteByPrimaryKeys(keyValues);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return this.sqlBuilder.batchDeleteByPrimaryKeys(keyValues);
    }

    @Override
    public SqlBuilder delete() {
        return this.sqlBuilder.delete();
    }
}
