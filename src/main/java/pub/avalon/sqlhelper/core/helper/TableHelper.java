package pub.avalon.sqlhelper.core.helper;

import java.util.Map;

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
     * 获取表的列名与别名键值对集合
     * key - 列名
     * value - 别名
     *
     * @return {@link java.util.LinkedHashMap}
     */
    Map<String, String> getColumnNameAliasMap();

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
     * 创建一个新的On sql模组
     *
     * @return {@link OnHelper}
     */
    TO newOnHelper();

    /**
     * 创建一个新的Column sql模组
     *
     * @return {@link ColumnHelper}
     */
    TC newColumnHelper();

    /**
     * 创建一个新的Where sql模组
     *
     * @return {@link WhereHelper}
     */
    TW newWhereHelper();

    /**
     * 创建一个新的Group sql模组
     *
     * @return {@link GroupHelper}
     */
    TG newGroupHelper();

    /**
     * 创建一个新的Sort sql模组
     *
     * @return {@link SortHelper}
     */
    TS newSortHelper();

}
