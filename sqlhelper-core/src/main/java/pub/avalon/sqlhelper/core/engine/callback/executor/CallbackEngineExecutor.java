package pub.avalon.sqlhelper.core.engine.callback.executor;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalon.sqlhelper.core.data.*;
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
public class CallbackEngineExecutor {

    private CallbackEngineExecutor() {
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeColumn(Class<F> tableHelperClass, String tableAlias, ColumnCallback<FC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        return CallbackExecutor.execute(tableHelperClass, tableAlias, columnCallback, sqlBuilderOptions);
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
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

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableColumnDatum executeVirtualColumn(Class<F> tableHelperClass, String tableAlias, Object columnValue, String columnAlias) {
        return columnAlias == null ? null : new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, columnValue, columnAlias)));
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
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


    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableGroupDatum execute(Class<F> tableHelperClass, String tableAlias, GroupCallback<FG> callback, SqlBuilderOptions sqlBuilderOptions) {
        return CallbackExecutor.execute(tableHelperClass, tableAlias, callback, sqlBuilderOptions);
    }

    public static <FO extends OnHelper<FO>,
            E extends TableHelper<E, EO, EC, EW, EG, EH, ES>,
            EO extends OnHelper<EO>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, FO mainOnHelper, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, OnJoinCallback<FO, EO> onJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        E e = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? e.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDatum tableOnDatum = CallbackExecutor.execute(mainOnHelper, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderOptions);
        joinTableDatum.setTableOnDatum(tableOnDatum);
        return joinTableDatum;
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>,
            E extends TableHelper<E, EO, EC, EW, EG, EH, ES>,
            EO extends OnHelper<EO>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, Class<F> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, OnJoinCallback<FO, EO> onJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        E e = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? e.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDatum tableOnDatum = CallbackExecutor.execute(mainTableHelperClass, mainTableAlias, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderOptions);
        joinTableDatum.setTableOnDatum(tableOnDatum);
        return joinTableDatum;
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableSortDatum execute(Class<F> tableHelperClass, String tableAlias, SortCallback<FS> sortCallback, SqlBuilderOptions sqlBuilderOptions) {
        return CallbackExecutor.execute(tableHelperClass, tableAlias, sortCallback, sqlBuilderOptions);
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableWhereDatum execute(Class<F> tableHelperClass, String tableAlias, WhereCallback<FW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        return CallbackExecutor.execute(tableHelperClass, tableAlias, whereCallback, sqlBuilderOptions);
    }

    public static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>,
            E extends TableHelper<E, EO, EC, EW, EG, EH, ES>,
            EO extends OnHelper<EO>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> TableWhereDatum execute(Class<F> mainTableHelperClass, String mainTableAlias, Class<E> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<FW, EW> whereJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        return CallbackExecutor.execute(mainTableHelperClass, mainTableAlias, joinTableHelperClass, joinTableAlias, whereJoinCallback, sqlBuilderOptions);
    }
}