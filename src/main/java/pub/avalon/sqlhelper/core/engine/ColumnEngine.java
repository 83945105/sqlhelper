package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.data.TableGroupColumnDatum;
import pub.avalon.sqlhelper.core.data.VirtualFieldDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    R subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback);

    static List<TableColumnDatum> executeColumn(ColumnHelper<?>... columnHelpers) {
        if (columnHelpers == null || columnHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(columnHelpers).map(columnHelper -> columnHelper.execute()).collect(Collectors.toList());
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeColumn(Class<F> tableHelperClass, String tableAlias, ColumnCallback<FC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        return ColumnCallback.execute(tableHelperClass, tableAlias, columnCallback, sqlBuilderOptions);
    }

    static VirtualFieldDatum executeVirtualColumn(Object value, String alias) {
        return alias == null ? null : new VirtualFieldDatum().setValue(value).setAlias(alias);
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableGroupColumnDatum executeGroupColumn(Class<F> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<FC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
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
        Set<ColumnDatum> columnData = fc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return null;
        }
        return new TableGroupColumnDatum(tableAlias, groupType, columnData);
    }
}
