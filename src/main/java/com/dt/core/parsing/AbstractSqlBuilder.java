package com.dt.core.parsing;

/**
 * @author 白超
 * @date 2018/8/20
 */
public abstract class AbstractSqlBuilder implements SqlBuilder {

    @Override
    public Object queryByPrimaryKey(Object keyValue) {
        return null;
    }

    @Override
    public Object queryOne() {
        return null;
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
