package com.dt.core.norm;

import com.dt.core.data.ParseData;

import java.util.Map;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface Engine {

    default Model getTable() {
        return null;
    }

    default Class getTableClass() {
        return null;
    }

    default String getTableName() {
        return null;
    }

    default String getTableAlias() {
        return null;
    }

    default String getPrimaryKeyName() {
        return null;
    }

    default String getPrimaryKeyAlias() {
        return null;
    }

    default Map<String, String> getColumnAliasMap() {
        return null;
    }

    default String getColumnSql() {
        return null;
    }

    default ParseData getJoinParseData() {
        return null;
    }

    default ParseData getWhereParseData() {
        return null;
    }

    default String getGroupSql() {
        return null;
    }

    default String getSortSql() {
        return null;
    }

    default ParseData getLimitParseData() {
        return null;
    }

}
