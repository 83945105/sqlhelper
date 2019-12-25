package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.engine.JdbcEngine;
import pub.avalonframework.sqlhelper.core.engine.callback.JdbcCallbackEngine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public abstract class Sql<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements JdbcEngine<Sql<T, TO, TC, TW, TG, TH, TS>>, JdbcCallbackEngine<TO, TC, TW, TG, TH, TS, Sql<T, TO, TC, TW, TG, TH, TS>> {

    private TO onHelper;
    private TC columnHelper;
    private TW whereHelper;
    private TG groupHelper;
    private TH havingHelper;
    private TS sortHelper;
    private String tableAlias;

    public Sql() {

    }

    public Sql(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> update(ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> limit(Long limit) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> offset(Long offset) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        return this;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>, SO extends OnHelper<SO>, SC extends ColumnHelper<SC>, SW extends WhereHelper<SW>, SG extends GroupHelper<SG>, SH extends HavingHelper<SH>, SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>, SO extends OnHelper<SO>, SC extends ColumnHelper<SC>, SW extends WhereHelper<SW>, SG extends GroupHelper<SG>, SH extends HavingHelper<SH>, SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> having(HavingCallback<TH> havingCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>, SO extends OnHelper<SO>, SC extends ColumnHelper<SC>, SW extends WhereHelper<SW>, SG extends GroupHelper<SG>, SH extends HavingHelper<SH>, SS extends SortHelper<SS>> Sql<T, TO, TC, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        return null;
    }

    @Override
    public Sql<T, TO, TC, TW, TG, TH, TS> having(HavingHelper<?>... havingHelpers) {
        return null;
    }
}