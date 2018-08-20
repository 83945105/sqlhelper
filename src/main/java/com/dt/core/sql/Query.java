package com.dt.core.sql;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Query extends Sql {

    Object queryByPrimaryKey(Object keyValue);

    Object queryOne();

    Object queryForList();

    Object queryCount();

    Object queryPairColumnInMap();

    Object queryPairColumnInMap(int keyIndex, int valueIndex);

    Object queryPairColumnInMap(String keyColumnName, String valueColumnName);

    Object queryForListInMap(int keyIndex);

    Object queryForListInMap(String keyColumnName);

}
