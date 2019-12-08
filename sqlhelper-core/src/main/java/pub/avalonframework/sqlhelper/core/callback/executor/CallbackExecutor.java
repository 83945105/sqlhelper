package pub.avalonframework.sqlhelper.core.callback.executor;

import pub.avalonframework.sqlhelper.core.beans.*;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.List;

/**
 * @author baichao
 */
public final class CallbackExecutor {

    private CallbackExecutor() {
    }

    public static <TC extends ColumnHelper<TC>> TableColumnDatum execute(TC columnHelper, ColumnCallback<TC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (columnHelper == null) {
            ExceptionUtils.columnHelperNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        columnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        columnHelper = columnCallback.apply(columnHelper);
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = HelperManager.defaultColumnData(columnHelper);
        }
        return new TableColumnDatum(columnHelper.getTableAlias(), columnData);
    }

    public static <TG extends GroupHelper<TG>> TableGroupDatum execute(TG groupHelper, GroupCallback<TG> groupCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (groupHelper == null) {
            ExceptionUtils.groupHelperNullException();
        }
        if (groupCallback == null) {
            return null;
        }
        groupHelper.setSqlBuilderOptions(sqlBuilderOptions);
        groupHelper = groupCallback.apply(groupHelper);
        List<GroupDatum> groupData = groupHelper.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return null;
        }
        return new TableGroupDatum(groupHelper.getTableAlias(), groupData);
    }

    public static <TS extends SortHelper<TS>> TableSortDatum execute(TS sortHelper, SortCallback<TS> sortCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (sortHelper == null) {
            ExceptionUtils.sortHelperNullException();
        }
        if (sortCallback == null) {
            return null;
        }
        sortHelper.setSqlBuilderOptions(sqlBuilderOptions);
        sortHelper = sortCallback.apply(sortHelper);
        List<SortDatum> sortData = sortHelper.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return null;
        }
        return new TableSortDatum(sortHelper.getTableAlias(), sortData);
    }

    public static <TO extends OnHelper<TO>> TableOnDatum execute(TO onHelper, OnCallback<TO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (onHelper == null) {
            ExceptionUtils.onHelperNullException();
        }
        if (onCallback == null) {
            return null;
        }
        onHelper.setSqlBuilderOptions(sqlBuilderOptions);
        OnLinker<TO> onLinker = onCallback.apply(new OnAndOr<>(), onHelper);
        List<ComparisonSqlPartDataLinker> onDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return null;
        }
        return new TableOnDatum(onHelper.getTableAlias(), onDataLinkers);
    }

    public static <TO extends OnHelper<TO>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableOnDatum execute(TO mainOnHelper, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainOnHelper == null) {
            ExceptionUtils.onHelperNullException();
        }
        if (onJoinCallback == null) {
            return null;
        }
        mainOnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SO so = s.newOnHelper(joinTableAlias);
        so.setSqlBuilderOptions(sqlBuilderOptions);
        OnLinker<TO> onLinker = onJoinCallback.apply(new OnAndOr<>(), so, mainOnHelper);
        List<ComparisonSqlPartDataLinker> onDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return null;
        }
        return new TableOnDatum(joinTableAlias, onDataLinkers);
    }

    public static <TW extends WhereHelper<TW>> TableWhereDatum execute(TW whereHelper, WhereCallback<TW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (whereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereCallback == null) {
            return null;
        }
        whereHelper.setSqlBuilderOptions(sqlBuilderOptions);
        WhereLinker<TW> whereLinker = whereCallback.apply(new WhereAndOr<>(), whereHelper);
        List<ComparisonSqlPartDataLinker> whereDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(whereHelper.getTableAlias(), whereDataLinkers);
    }

    public static <TW extends WhereHelper<TW>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableWhereDatum execute(TW mainWhereHelper, Class<S> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<TW, SW> whereJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainWhereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereJoinCallback == null) {
            return null;
        }
        mainWhereHelper.setSqlBuilderOptions(sqlBuilderOptions);
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SW sw = s.newWhereHelper(joinTableAlias);
        sw.setSqlBuilderOptions(sqlBuilderOptions);
        WhereLinker<TW> whereLinker = whereJoinCallback.apply(new WhereAndOr<>(), sw, mainWhereHelper);
        List<ComparisonSqlPartDataLinker> whereDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(joinTableAlias, whereDataLinkers);
    }

    public static <TH extends HavingHelper<TH>> TableHavingDatum execute(TH havingHelper, HavingCallback<TH> havingCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (havingHelper == null) {
            ExceptionUtils.havingHelperNullException();
        }
        if (havingCallback == null) {
            return null;
        }
        havingHelper.setSqlBuilderOptions(sqlBuilderOptions);
        HavingLinker<TH> havingLinker = havingCallback.apply(new HavingAndOr<>(), havingHelper);
        List<ComparisonSqlPartDataLinker> havingDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (havingDataLinkers == null || havingDataLinkers.size() == 0) {
            return null;
        }
        return new TableHavingDatum(havingHelper.getTableAlias(), havingDataLinkers);
    }

    public static <TH extends HavingHelper<TH>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableHavingDatum execute(TH mainHavingHelper, Class<S> joinTableHelperClass, String joinTableAlias, HavingJoinCallback<TH, SH> havingJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainHavingHelper == null) {
            ExceptionUtils.havingHelperNullException();
        }
        if (havingJoinCallback == null) {
            return null;
        }
        mainHavingHelper.setSqlBuilderOptions(sqlBuilderOptions);
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SH sh = s.newHavingHelper(joinTableAlias);
        sh.setSqlBuilderOptions(sqlBuilderOptions);
        HavingLinker<TH> havingLinker = havingJoinCallback.apply(new HavingAndOr<>(), sh, mainHavingHelper);
        List<ComparisonSqlPartDataLinker> havingDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (havingDataLinkers == null || havingDataLinkers.size() == 0) {
            return null;
        }
        return new TableHavingDatum(joinTableAlias, havingDataLinkers);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDatum execute(Class<T> tableHelperClass, String tableAlias, ColumnCallback<TC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tc, columnCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableGroupDatum execute(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> groupCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TG tg = t.newGroupHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tg, groupCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableSortDatum execute(Class<T> tableHelperClass, String tableAlias, SortCallback<TS> sortCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TS ts = t.newSortHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(ts, sortCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableOnDatum execute(Class<T> tableHelperClass, String tableAlias, OnCallback<TO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TO to = t.newOnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(to, onCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableOnDatum execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.newTableHelper(mainTableHelperClass);
        TO to = t.newOnHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(to, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableWhereDatum execute(Class<T> tableHelperClass, String tableAlias, WhereCallback<TW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TW tw = t.newWhereHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tw, whereCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableWhereDatum execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<TW, SW> whereJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(mainTableHelperClass);
        TW tw = t.newWhereHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(tw, joinTableHelperClass, joinTableAlias, whereJoinCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableHavingDatum execute(Class<T> tableHelperClass, String tableAlias, HavingCallback<TH> havingCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TH th = t.newHavingHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(th, havingCallback, sqlBuilderOptions);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableHavingDatum execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, HavingJoinCallback<TH, SH> havingJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(mainTableHelperClass);
        TH th = t.newHavingHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(th, joinTableHelperClass, joinTableAlias, havingJoinCallback, sqlBuilderOptions);
    }

    public static SqlBuilderResult execute(SubQueryCallback subQueryCallback) {
        SelectSqlBuilderResult selectSqlBuilderResult = subQueryCallback.apply();
        return FinalSqlBuilderResult.newInstance(selectSqlBuilderResult.getPreparedStatementSql(), selectSqlBuilderResult.getPreparedStatementArgs());
    }
}