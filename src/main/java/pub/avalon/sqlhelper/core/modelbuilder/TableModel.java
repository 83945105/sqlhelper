package pub.avalon.sqlhelper.core.modelbuilder;

import java.util.Map;

/**
 * 表模组
 *
 * @author 白超
 * @date 2019/5/15
 */
public interface TableModel<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> {

    /**
     * 获取表的列名与别名键值对集合
     * key - 列名
     * value - 别名
     *
     * @return {@link java.util.LinkedHashMap}
     */
    Map<String, String> getColumnAliasMap();

    /**
     * 获取表名
     *
     * @return {@link java.lang.String}
     */
    String getTableName();

    /**
     * 获取表别名
     *
     * @return {@link java.lang.String}
     */
    String getTableAlias();

    /**
     * 获取主键名称
     *
     * @return {@link java.lang.String}
     */
    String getPrimaryKeyName();

    /**
     * 获取主键别名
     *
     * @return {@link java.lang.String}
     */
    String getPrimaryKeyAlias();

    /**
     * 创建一个新的On sql模组
     *
     * @return {@link OnSqlModel}
     */
    TO newOnSqlModel();

    /**
     * 创建一个新的Column sql模组
     *
     * @return {@link ColumnSqlModel}
     */
    TC newColumnSqlModel();

    /**
     * 创建一个新的Where sql模组
     *
     * @return {@link WhereSqlModel}
     */
    TW newWhereSqlModel();

    /**
     * 创建一个新的Group sql模组
     *
     * @return {@link GroupSqlModel}
     */
    TG newGroupSqlModel();

    /**
     * 创建一个新的Sort sql模组
     *
     * @return {@link SortSqlModel}
     */
    TS newSortSqlModel();

}
