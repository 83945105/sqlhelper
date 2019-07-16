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
public interface ColumnEngine<TC extends ColumnHelper<TC>, R extends ColumnEngine<TC, R>> {

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
     * @param callback {@link ColumnCallback}
     * @return {@link ColumnEngine}
     */
    R column(ColumnCallback<TC> callback);

    /**
     * 执行指定列回调
     *
     * @param tableHelperClass 表助手
     * @param tableAlias       表别名
     * @param callback         {@link ColumnCallback}
     * @return {@link ColumnEngine}
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback);

    /**
     * 执行指定列回调
     *
     * @param tableHelperClass 表助手
     * @param callback         {@link ColumnCallback}
     * @return {@link ColumnEngine}
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R column(Class<S> tableHelperClass, ColumnCallback<SC> callback) {
        return column(tableHelperClass, null, callback);
    }

    /**
     * 虚拟列
     *
     * @param value 值
     * @param alias 别名
     * @return {@link ColumnEngine}
     */
    R virtualColumn(Object value, String alias);


    R groupColumn(GroupType groupType, ColumnCallback<TC> callback);

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnCallback<SC> callback) {
        return groupColumn(tableHelperClass, null, groupType, callback);
    }

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R subQuery(String tableName, Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(tableName, tableHelperClass, null, callback, columnAlias);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R subQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, null, callback, columnAlias);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R subQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, tableAlias, callback, columnAlias);
    }

}
