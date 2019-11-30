package pub.avalonframework.sqlhelper.core.engine.callback.executor;

import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.ColumnDatum;
import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;
import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public class CallbackEngineExecutor {

    private CallbackEngineExecutor() {
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDatum executeGroupColumn(Class<T> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<TC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias);
        tc.setSqlBuilderOptions(sqlBuilderOptions);
        tc = columnCallback.apply(tc);
        List<ColumnDatum> columnData = tc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return null;
        }
        columnData.forEach(columnDatum -> columnDatum.setColumnHandlers(groupType));
        return new TableColumnDatum(tableAlias, columnData);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDatum executeVirtualColumn(Class<T> tableHelperClass, String tableAlias, Object columnValue, String columnAlias) {
        return columnAlias == null ? null : new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, columnValue, columnAlias)));
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDatum executeSubQueryColumn(Class<T> tableHelperClass, String tableAlias, String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (columnAlias == null) {
            return null;
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias);
        tc.setSqlBuilderOptions(sqlBuilderOptions);
        SqlBuilderResult sqlBuilderResult = subQueryColumnCallback.apply(tc);
        return new TableColumnDatum(tableAlias, Collections.singletonList(new ColumnDatum(null, null, sqlBuilderResult, columnAlias)));
    }

    public static <TO extends OnHelper<TO>,
            S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JoinTableDatum execute(JoinType joinType, TO mainOnHelper, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? s.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDatum tableOnDatum = CallbackExecutor.execute(mainOnHelper, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderOptions);
        joinTableDatum.setTableOnDatum(tableOnDatum);
        return joinTableDatum;
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
            SS extends SortHelper<SS>> JoinTableDatum execute(JoinType joinType, Class<T> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? s.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDatum tableOnDatum = CallbackExecutor.execute(mainTableHelperClass, mainTableAlias, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderOptions);
        joinTableDatum.setTableOnDatum(tableOnDatum);
        return joinTableDatum;
    }
}