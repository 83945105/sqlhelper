package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

/**
 * @author baichao
 */
public interface JoinCallbackEngine<TO extends OnHelper<TO>, R> extends Engine {

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback);

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(joinType, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(joinType, tableName, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
        return join(joinType, tableName, tableHelperClass, null, null);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(joinType, null, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
        return join(joinType, null, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(joinType, null, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass) {
        return join(joinType, null, tableHelperClass, null, null);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(JoinType.INNER, tableName, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.INNER, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, null);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(JoinType.INNER, null, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.INNER, null, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(JoinType.INNER, null, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass) {
        return join(JoinType.INNER, null, tableHelperClass, null, null);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.LEFT, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, null);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(JoinType.LEFT, null, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.LEFT, null, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(JoinType.LEFT, null, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass) {
        return join(JoinType.LEFT, null, tableHelperClass, null, null);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, null);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> onCallback) {
        return join(JoinType.RIGHT, null, tableHelperClass, tableAlias, onCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.RIGHT, null, tableHelperClass, tableAlias, null);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onCallback       {@link OnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, OnCallback<TO, SO> onCallback) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, onCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, null);
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
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
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, Class<F> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, OnCallback<FO, EO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        F f = HelperManager.defaultTableHelper(mainTableHelperClass);
        FO fj = f.newOnHelper(mainTableAlias);
        return execute(joinType, fj, joinTableName, joinTableHelperClass, joinTableAlias, onCallback, sqlBuilderOptions);
    }

    static <FO extends OnHelper<FO>,
            E extends TableHelper<E, EO, EC, EW, EG, EH, ES>,
            EO extends OnHelper<EO>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, FO mainOnHelper, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, OnCallback<FO, EO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        E e = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? e.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        EO ej = e.newOnHelper(joinTableAlias);
        ej.setSqlBuilderOptions(sqlBuilderOptions);
        mainOnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        if (onCallback == null) {
            return joinTableDatum;
        }
        TableOnDatum tableOnDatum = CallbackExecutor.execute(mainOnHelper, ej, onCallback, sqlBuilderOptions);
        if (tableOnDatum == null) {
            return joinTableDatum;
        }
        joinTableDatum.setTableOnDatum(tableOnDatum);
        return joinTableDatum;
    }
}