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
public interface JoinEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> innerJoin(String tableName, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> innerJoin(Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> innerJoin(Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> leftJoin(String tableName, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> leftJoin(Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> leftJoin(Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> rightJoin(String tableName, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> rightJoin(Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableHelperClass, alias, callback);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> rightJoin(Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, callback);
    }

}
