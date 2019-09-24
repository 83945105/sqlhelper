package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public interface ColumnCallbackEngine<TC extends ColumnHelper<TC>, R> extends Engine {

    /**
     * use callback to add column sql data
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R column(ColumnCallback<TC> columnCallback);

    /**
     * use callback to add select column sql data
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R select(ColumnCallback<TC> columnCallback);

    /**
     * use callback to add insert column sql data
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R insert(ColumnCallback<TC> columnCallback);

    /**
     * use callback to add update column sql data
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R update(ColumnCallback<TC> columnCallback);

    /**
     * use callback to add assign class column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * use callback to add assign class select column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * use callback to add assign class column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R column(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return column(tableHelperClass, null, columnCallback);
    }

    /**
     * use callback to add assign class select column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R select(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return select(tableHelperClass, null, columnCallback);
    }

    /**
     * use callback to add group column sql data
     *
     * @param groupType      {@link GroupType}
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback);

    /**
     * use callback to add assign class group column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param groupType        {@link GroupType}
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback);

    /**
     * use callback to add assign class group column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param groupType        {@link GroupType}
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return groupColumn(tableHelperClass, null, groupType, columnCallback);
    }

    /**
     * use callback to add sub query column sql data
     *
     * @param columnAlias            column alias
     * @param subQueryColumnCallback {@link SubQueryColumnCallback}
     * @return R
     */
    R subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback);

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeColumn(Class<F> tableHelperClass, String tableAlias, ColumnCallback<FC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        return ColumnCallback.execute(tableHelperClass, tableAlias, columnCallback, sqlBuilderOptions);
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
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
        F f = HelperManager.defaultTableHelper(tableHelperClass);
        FC fc = f.newColumnHelper(tableAlias);
        fc.setSqlBuilderOptions(sqlBuilderOptions);
        fc = columnCallback.apply(fc);
        List<ColumnDatum> columnData = fc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return null;
        }
        columnData.forEach(columnDatum -> columnDatum.setColumnHandlers(groupType));
        return new TableColumnDatum(tableAlias, columnData);
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeVirtualColumn(Class<F> tableHelperClass, String tableAlias, Object columnValue, String columnAlias) {
        return columnAlias == null ? null : new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, columnValue, columnAlias)));
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeSubQueryColumn(Class<F> tableHelperClass, String tableAlias, String columnAlias, SubQueryColumnCallback<FC> subQueryColumnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (columnAlias == null) {
            return null;
        }
        F f = HelperManager.defaultTableHelper(tableHelperClass);
        FC fc = f.newColumnHelper(tableAlias);
        fc.setSqlBuilderOptions(sqlBuilderOptions);
        SqlBuilderResult sqlBuilderResult = subQueryColumnCallback.apply(fc);
        return new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, sqlBuilderResult, columnAlias)));
    }
}