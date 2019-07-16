package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 连接引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface JoinEngine<TO extends OnHelper<TO>, R extends JoinEngine<TO, R>> {

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, String tableAlias, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableHelperClass, tableAlias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, OnCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, callback);
    }

}
