package com.dt.core.sql;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Query<T> extends Sql {

    T queryByPrimaryKey(Object keyValue);

    T queryOne();

    T queryForList();

    T queryCount();

    T queryPairColumnInMap();

    T queryPairColumnInMap(int keyIndex, int valueIndex);

    T queryPairColumnInMap(String keyColumnName, String valueColumnName);

    T queryForListInMap(int keyIndex);

    T queryForListInMap(String keyColumnName);

}
