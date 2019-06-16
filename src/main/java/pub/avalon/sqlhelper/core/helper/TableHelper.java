package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.TableColumn;

import java.util.Set;

/**
 * 表助手
 *
 * @author 白超
 * @date 2019/5/15
 */
public interface TableHelper<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    /**
     * 获取表名
     *
     * @return {@link String}
     */
    String getTableName();

    /**
     * 获取表别名
     *
     * @return {@link String}
     */
    String getTableAlias();

    /**
     * 获取主键名称
     *
     * @return {@link String}
     */
    String getPrimaryKeyName();

    /**
     * 获取主键别名
     *
     * @return {@link String}
     */
    String getPrimaryKeyAlias();

    /**
     * 获取表列数据
     *
     * @return {@link java.util.LinkedHashSet}
     */
    Set<TableColumn> getTableColumns();

    /**
     * 创建助手
     *
     * @return {@link Helper}
     */
    T newHelper();

    /**
     * 创建On助手
     *
     * @return {@link OnHelper}
     */
    TO newOnHelper();

    /**
     * 创建列助手
     *
     * @return {@link ColumnHelper}
     */
    TC newColumnHelper();

    /**
     * 创建条件助手
     *
     * @return {@link WhereHelper}
     */
    TW newWhereHelper();

    /**
     * 创建分组助手
     *
     * @return {@link GroupHelper}
     */
    TG newGroupHelper();

    /**
     * 创建排序助手
     *
     * @return {@link SortHelper}
     */
    TS newSortHelper();

}
