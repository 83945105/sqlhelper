package com.dt.core.parsing;

import com.dt.core.data.SqlData;
import com.dt.core.sql.Query;

/**
 * Sql构建代理器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class SqlBuilderProxy implements Query {

    protected SqlData sqlData;

    private SqlBuilder sqlBuilder;

    public SqlBuilderProxy(SqlData sqlData) {
        this.sqlData = sqlData;
        switch (this.sqlData.getDataBaseType()) {
            case MYSQL:
                this.sqlBuilder = new MySqlBuilder();
                break;
            default:
        }
    }

    @Override
    public Object queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilder.queryByPrimaryKey(keyValue);
    }

    @Override
    public Object queryOne() {
        return this.sqlBuilder.queryOne();
    }

    @Override
    public Object queryForList() {
        return null;
    }

    @Override
    public Object queryCount() {
        return null;
    }

    @Override
    public Object queryPairColumnInMap() {
        return null;
    }

    @Override
    public Object queryPairColumnInMap(int keyIndex, int valueIndex) {
        return null;
    }

    @Override
    public Object queryPairColumnInMap(String keyColumnName, String valueColumnName) {
        return null;
    }

    @Override
    public Object queryForListInMap(int keyIndex) {
        return null;
    }

    @Override
    public Object queryForListInMap(String keyColumnName) {
        return null;
    }
}
