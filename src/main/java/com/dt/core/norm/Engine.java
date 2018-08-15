package com.dt.core.norm;

import java.util.Map;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface Engine {

    /**
     * 获取表模组对象
     *
     * @return 模组
     */
    default Model getTableModel() {
        return null;
    }

    /**
     * 获取表类型
     *
     * @return 表类型
     */
    default Class getTableClass() {
        return null;
    }

    /**
     * 获取表名
     *
     * @return 表名
     */
    default String getTableName() {
        return null;
    }

    /**
     * 获取表别名
     *
     * @return 表别名
     */
    default String getTableAlias() {
        return null;
    }

    /**
     * 获取主键名
     *
     * @return 主键名
     */
    default String getPrimaryKeyName() {
        return null;
    }

    /**
     * 获取主键别名
     *
     * @return 主键别名
     */
    default String getPrimaryKeyAlias() {
        return null;
    }

    /**
     * 获取列-别名集合
     *
     * @return 列-别名集合
     */
    default Map<String, String> getColumnAliasMap() {
        return null;
    }

}
