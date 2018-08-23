package com.dt.core.build;

import com.dt.core.data.SqlData;
import com.dt.core.sql.Query;
import com.dt.core.sql.Table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Sql构建代理器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class SqlBuilderProxy implements Table<SqlBuilder>, Query<SqlBuilder> {

    @Autowired(required = false)
    private SqlBuilder sqlBuilder;

    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    public SqlBuilderProxy(SqlData sqlData) {
        if (this.sqlBuilder == null) {
            switch (sqlData.getDataBaseType()) {
                case MYSQL:
                    this.sqlBuilder = new MySqlBuilder(sqlData);
                    break;
                default:
            }
        }
    }

    @Override
    public SqlBuilder copyTable(String sourceTableName, String targetTableName) {
        return this.sqlBuilder.copyTable(sourceTableName, targetTableName);
    }

    @Override
    public SqlBuilder deleteTable(String tableName) {
        return this.sqlBuilder.deleteTable(tableName);
    }

    @Override
    public SqlBuilder renameTable(String sourceTableName, String targetTableName) {
        return this.sqlBuilder.renameTable(sourceTableName, targetTableName);
    }

    @Override
    public SqlBuilder isTableExist(String tableName) {
        return this.sqlBuilder.isTableExist(tableName);
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilder.queryByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder queryOne() {
        return this.sqlBuilder.queryOne();
    }

    @Override
    public SqlBuilder queryForList() {
        return this.sqlBuilder.queryForList();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilder.queryCount();
    }

    @Override
    public SqlBuilder queryPairColumnInMap() {
        return this.sqlBuilder.queryPairColumnInMap();
    }

    @Override
    public SqlBuilder queryPairColumnInMap(int keyIndex, int valueIndex) {
        return this.sqlBuilder.queryPairColumnInMap(keyIndex, valueIndex);
    }

    @Override
    public SqlBuilder queryPairColumnInMap(String keyColumnName, String valueColumnName) {
        return this.sqlBuilder.queryPairColumnInMap(keyColumnName, valueColumnName);
    }

    @Override
    public SqlBuilder queryForListInMap(int keyIndex) {
        return this.sqlBuilder.queryForListInMap(keyIndex);
    }

    @Override
    public SqlBuilder queryForListInMap(String keyColumnName) {
        return this.sqlBuilder.queryForListInMap(keyColumnName);
    }

}
