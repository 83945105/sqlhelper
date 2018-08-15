package com.dt.core.norm;

import com.dt.core.bean.*;

import java.util.Map;

/**
 * 模组
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface Model<T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    /**
     * 获取表名
     *
     * @return 表名
     */
    String getTableName();

    /**
     * 获取表别名
     *
     * @return 表别名
     */
    String getTableAlias();

    /**
     * 获取主键名
     *
     * @return 主键名
     */
    String getPrimaryKeyName();

    /**
     * 获取主键别名
     *
     * @return 主键别名
     */
    String getPrimaryKeyAlias();

    /**
     * 获取列-别名集合
     *
     * @return 列-别名集合
     */
    Map<String, String> getColumnAliasMap();

    /**
     * 获取别名-列集合
     *
     * @return 别名-列集合
     */
    Map<String, String> getAliasColumnMap();

    /**
     * 获取列模组
     *
     * @return 列模组
     */
    ColumnModel<T, TL, TO, TC, TS, TG> getColumnModel();

    /**
     * 获取On条件模组
     *
     * @return On条件模组
     */
    OnModel<T, TL, TO, TC, TS, TG> getOnModel();

    /**
     * 获取Where条件模组
     *
     * @return Where条件模组
     */
    WhereModel<T, TL, TO, TC, TS, TG> getWhereModel();

    /**
     * 获取分组模组
     *
     * @return 分组模组
     */
    GroupModel<T, TL, TO, TC, TS, TG> getGroupModel();

    /**
     * 获取排序模组
     *
     * @return 排序模组
     */
    SortModel<T, TL, TO, TC, TS, TG> getSortModel();

}
