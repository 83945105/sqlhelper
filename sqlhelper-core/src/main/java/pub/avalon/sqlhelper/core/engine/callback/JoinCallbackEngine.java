package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.JoinCallback;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback);

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(joinType, tableName, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(joinType, null, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add join sql data
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(joinType, null, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.INNER, tableName, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.INNER, null, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add inner join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.INNER, null, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.LEFT, null, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add left join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.LEFT, null, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, joinCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, String tableAlias, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.RIGHT, null, tableHelperClass, tableAlias, joinCallback);
    }

    /**
     * use callback to add right join sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param joinCallback     {@link JoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, JoinCallback<TO, SO> joinCallback) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, joinCallback);
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>,
            E extends TableHelper<E, EJ, EC, EW, EG, EH, ES>,
            EJ extends OnHelper<EJ>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, Class<F> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, JoinCallback<FO, EJ> joinCallback, SqlBuilderOptions sqlBuilderOptions) {
        return JoinCallback.execute(joinType, mainTableHelperClass, mainTableAlias, joinTableName, joinTableHelperClass, joinTableAlias, joinCallback, sqlBuilderOptions);
    }
}