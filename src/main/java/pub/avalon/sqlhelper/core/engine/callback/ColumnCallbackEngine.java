package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.engine.ColumnEngine;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/31
 */
public interface ColumnCallbackEngine<TC extends ColumnHelper<TC>, R> extends Engine {

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

    /**
     * 子查询列
     *
     * @param columnAlias            列别名
     * @param subQueryColumnCallback 子查询列回调
     * @return {@link ColumnEngine}
     */
    R subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback);

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeColumn(Class<F> tableHelperClass, String tableAlias, ColumnCallback<FC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        return ColumnCallback.execute(tableHelperClass, tableAlias, columnCallback, sqlBuilderOptions);
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeGroupColumn(Class<F> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<FC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        FC fc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper(tableAlias);
        // 设置配置开始
        fc.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        fc = columnCallback.apply(fc);
        List<ColumnDatum> columnData = fc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return null;
        }
        columnData.forEach(columnDatum -> columnDatum.setColumnHandlers(groupType));
        return new TableColumnDatum(tableAlias, columnData);
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeVirtualColumn(Class<F> tableHelperClass, String tableAlias, Object columnValue, String columnAlias) {
        return columnAlias == null ? null : new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, columnValue, columnAlias)));
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeSubQueryColumn(Class<F> tableHelperClass, String tableAlias, String columnAlias, SubQueryColumnCallback<FC> subQueryColumnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (columnAlias == null) {
            return null;
        }
        FC fc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper(tableAlias);
        // 设置配置开始
        fc.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        SqlBuilderResult sqlBuilderResult = subQueryColumnCallback.apply(fc);
        return new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, sqlBuilderResult, columnAlias)));
    }
}
