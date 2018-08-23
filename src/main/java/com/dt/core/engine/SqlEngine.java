package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.FinalSqlData;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.Model;
import com.dt.core.build.SqlBuilder;
import com.dt.core.build.SqlBuilderProxy;
import com.dt.core.sql.Query;
import com.dt.core.sql.Table;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public class SqlEngine<M extends Model> implements Table<SqlBuilder>, Query<SqlBuilder> {

    protected FinalSqlData<M> sqlData;

    private SqlBuilderProxy sqlBuilderProxy;

    SqlEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        MainTableData<M> data = new MainTableData<>(mainClass);
        this.sqlData = new FinalSqlData<>(dataBaseType);
        this.sqlData.setMainTableData(data);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    SqlEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        MainTableData<M> data = new MainTableData<>(mainClass);
        data.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(dataBaseType);
        this.sqlData.setMainTableData(data);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    @Override
    public SqlBuilder copyTable(String sourceTableName, String targetTableName) {
        return this.sqlBuilderProxy.copyTable(sourceTableName, targetTableName);
    }

    @Override
    public SqlBuilder deleteTable(String tableName) {
        return this.sqlBuilderProxy.deleteTable(tableName);
    }

    @Override
    public SqlBuilder renameTable(String sourceTableName, String targetTableName) {
        return this.sqlBuilderProxy.renameTable(sourceTableName, targetTableName);
    }

    @Override
    public SqlBuilder isTableExist(String tableName) {
        return this.sqlBuilderProxy.isTableExist(tableName);
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilderProxy.queryByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder queryOne() {
        return this.sqlBuilderProxy.queryOne();
    }

    @Override
    public SqlBuilder queryForList() {
        return this.sqlBuilderProxy.queryForList();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilderProxy.queryCount();
    }

    @Override
    public SqlBuilder queryPairColumnInMap() {
        return this.sqlBuilderProxy.queryPairColumnInMap();
    }

    @Override
    public SqlBuilder queryPairColumnInMap(int keyIndex, int valueIndex) {
        return this.sqlBuilderProxy.queryPairColumnInMap(keyIndex, valueIndex);
    }

    @Override
    public SqlBuilder queryPairColumnInMap(String keyColumnName, String valueColumnName) {
        return this.sqlBuilderProxy.queryPairColumnInMap(keyColumnName, valueColumnName);
    }

    @Override
    public SqlBuilder queryForListInMap(int keyIndex) {
        return this.sqlBuilderProxy.queryForListInMap(keyIndex);
    }

    @Override
    public SqlBuilder queryForListInMap(String keyColumnName) {
        return this.sqlBuilderProxy.queryForListInMap(keyColumnName);
    }





















    /*
     *//**
     * 获取表模组对象
     *
     * @return 模组
     *//*
    default Model getTableModel() {
        return null;
    }

    *//**
     * 获取表类型
     *
     * @return 表类型
     *//*
    default Class getTableClass() {
        return null;
    }

    *//**
     * 获取表名
     *
     * @return 表名
     *//*
    default String getTableName() {
        return null;
    }

    *//**
     * 获取表别名
     *
     * @return 表别名
     *//*
    default String getTableAlias() {
        return null;
    }

    *//**
     * 获取主键名
     *
     * @return 主键名
     *//*
    default String getPrimaryKeyName() {
        return null;
    }

    *//**
     * 获取主键别名
     *
     * @return 主键别名
     *//*
    default String getPrimaryKeyAlias() {
        return null;
    }

    *//**
     * 获取列-别名集合
     *
     * @return 列-别名集合
     *//*
    default Map<String, String> getColumnAliasMap() {
        return null;
    }*/

}
