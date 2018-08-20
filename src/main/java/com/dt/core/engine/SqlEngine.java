package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.FinalSqlData;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.Model;
import com.dt.core.parsing.SqlBuilderProxy;
import com.dt.core.sql.Query;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public class SqlEngine<M extends Model> implements Query {

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
    public Object queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilderProxy.queryByPrimaryKey(keyValue);
    }

    @Override
    public Object queryOne() {
        return this.sqlBuilderProxy.queryOne();
    }

    @Override
    public Object queryForList() {
        return this.sqlBuilderProxy.queryForList();
    }

    @Override
    public Object queryCount() {
        return this.sqlBuilderProxy.queryCount();
    }

    @Override
    public Object queryPairColumnInMap() {
        return this.sqlBuilderProxy.queryPairColumnInMap();
    }

    @Override
    public Object queryPairColumnInMap(int keyIndex, int valueIndex) {
        return this.sqlBuilderProxy.queryPairColumnInMap(keyIndex, valueIndex);
    }

    @Override
    public Object queryPairColumnInMap(String keyColumnName, String valueColumnName) {
        return this.sqlBuilderProxy.queryPairColumnInMap(keyColumnName, valueColumnName);
    }

    @Override
    public Object queryForListInMap(int keyIndex) {
        return this.sqlBuilderProxy.queryForListInMap(keyIndex);
    }

    @Override
    public Object queryForListInMap(String keyColumnName) {
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
