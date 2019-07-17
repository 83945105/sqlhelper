package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql
 *
 * @author 白超
 * @date 2019/7/16
 */
public abstract class Sql<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> implements TableEngine<T, TO, TC, TW, TG, TS, Sql<T, TO, TC, TW, TG, TS>> {

    @Override
    public Sql<T, TO, TC, TW, TG, TS> column(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> virtualColumn(Object value, String alias) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> group(GroupHelper<?>... groupHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> callback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> limitTop(Long num) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> limitOne() {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> limit(LimitSql limit) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> limit(Long total, Long currentPage, Long pageSize) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>, SO extends OnHelper<SO>, SC extends ColumnHelper<SC>, SW extends WhereHelper<SW>, SG extends GroupHelper<SG>, SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TS> where(WhereCallback<TW> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        return null;
    }
}
