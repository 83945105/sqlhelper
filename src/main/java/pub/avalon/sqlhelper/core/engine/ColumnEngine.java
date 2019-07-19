package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface ColumnEngine<TC extends ColumnHelper<TC>, R> {

    /**
     * 设置列
     *
     * @param columnHelpers {@link ColumnHelper}
     * @return {@link ColumnEngine}
     */
    R column(ColumnHelper<?>... columnHelpers);

    /**
     * 执行列回调
     *
     * @param columnCallback {@link ColumnCallback}
     * @return {@link ColumnEngine}
     */
    R column(ColumnCallback<TC> columnCallback);

    /**
     * 执行指定列回调
     *
     * @param tableHelperClass 表助手
     * @param tableAlias       表别名
     * @param columnCallback   {@link ColumnCallback}
     * @return {@link ColumnEngine}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * 执行指定列回调
     *
     * @param tableHelperClass 表助手
     * @param columnCallback   {@link ColumnCallback}
     * @return {@link ColumnEngine}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R column(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return column(tableHelperClass, null, columnCallback);
    }

    /**
     * 虚拟列
     *
     * @param value 值
     * @param alias 别名
     * @return {@link ColumnEngine}
     */
    R virtualColumn(Object value, String alias);

    /**
     * 聚合列
     *
     * @param groupType      聚合类型
     * @param columnCallback 列回调
     * @return {@link ColumnEngine}
     */
    R groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback);

    /**
     * 聚合列
     *
     * @param tableHelperClass 表助手
     * @param tableAlias       表别名
     * @param groupType        聚合类型
     * @param columnCallback   列回调
     * @return {@link ColumnEngine}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback);

    /**
     * 聚合列
     *
     * @param tableHelperClass 表助手
     * @param groupType        聚合类型
     * @param columnCallback   列回调
     * @return {@link ColumnEngine}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return groupColumn(tableHelperClass, null, groupType, columnCallback);
    }

    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias);

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R subQuery(String tableName, Class<S> tableHelperClass, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias) {
        return this.subQuery(tableName, tableHelperClass, null, callback, columnAlias);
    }

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R subQuery(Class<S> tableHelperClass, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, null, callback, columnAlias);
    }

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R subQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, tableAlias, callback, columnAlias);
    }

}
