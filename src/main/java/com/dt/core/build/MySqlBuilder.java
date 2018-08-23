package com.dt.core.build;

import com.dt.core.data.SqlData;

import java.util.ArrayList;

/**
 * MySql Sql 构建器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class MySqlBuilder extends AbstractMySqlBuilder {

    public MySqlBuilder(SqlData sqlData) {
        super(sqlData);
    }

    @Override
    public SqlBuilder copyTable(String sourceTableName, String targetTableName) {
        this.sqlSplicer.clear()
                .append("create table ")
                .append(targetTableName)
                .append(" like ")
                .append(sourceTableName);
        this.sqlArgs = new ArrayList(0);
        return this;
    }

    @Override
    public SqlBuilder deleteTable(String tableName) {
        this.sqlSplicer.clear().append("drop table ").append(tableName);
        this.sqlArgs = new ArrayList(0);
        return this;
    }

    @Override
    public SqlBuilder renameTable(String sourceTableName, String targetTableName) {
        this.sqlSplicer.clear()
                .append("rename table ")
                .append(sourceTableName)
                .append(" to ")
                .append(targetTableName);
        this.sqlArgs = new ArrayList(0);
        return this;
    }

    @Override
    public SqlBuilder isTableExist(String tableName) {
        this.sqlSplicer.clear()
                .append("select count(*) from information_schema.TABLES where table_name = '")
                .append(tableName)
                .append("'");
        this.sqlArgs = new ArrayList(0);
        return this;
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        this.sqlSplicer.clear().append("select ");
        this.appendColumnSql(this.sqlSplicer);
        this.sqlSplicer.append(" from ")
                .append(this.sqlData.getMainTableData().getTableName())
                .append(" ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(" where ")
                .append(this.sqlData.getMainTableData().getTableAlias())
                .append(".")
                .append(this.sqlData.getMainTableData().getPrimaryKeyName())
                .append(" = ?");
        this.sqlArgs = new ArrayList<>(1);
        this.sqlArgs.add(keyValue);
        return this;
    }

    @Override
    public SqlBuilder queryOne() {
        return null;
    }

    @Override
    public SqlBuilder queryForList() {
        return null;
    }

    @Override
    public SqlBuilder queryCount() {
        return null;
    }

    @Override
    public SqlBuilder queryPairColumnInMap() {
        return null;
    }

    @Override
    public SqlBuilder queryPairColumnInMap(int keyIndex, int valueIndex) {
        return null;
    }

    @Override
    public SqlBuilder queryPairColumnInMap(String keyColumnName, String valueColumnName) {
        return null;
    }

    @Override
    public SqlBuilder queryForListInMap(int keyIndex) {
        return null;
    }

    @Override
    public SqlBuilder queryForListInMap(String keyColumnName) {
        return null;
    }

}
