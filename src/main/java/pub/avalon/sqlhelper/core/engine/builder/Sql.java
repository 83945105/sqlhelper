package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.engine.JdbcEngine;
import pub.avalon.sqlhelper.core.engine.callback.JdbcCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql
 *
 * @author 白超
 * @date 2019/7/16
 */
public abstract class Sql<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements JdbcEngine<Sql<T, TJ, TC, TW, TG, TH, TS>>, JdbcCallbackEngine<TJ, TC, TW, TG, TH, TS, Sql<T, TJ, TC, TW, TG, TH, TS>> {

    private TJ joinHelper;
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
    public Sql<T, TJ, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TJ, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TJ, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> group(GroupHelper<?>... groupHelpers) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> group(GroupCallback<TG> callback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TJ, TC, TW, TG, TH, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TJ, SJ> joinCallback) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> limitTop(Long num) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> limitOne() {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> limit(LimitSql limit) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> limit(Long total, Long currentPage, Long pageSize) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> sort(SortHelper<?>... sortHelpers) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> sort(SortCallback<TS> callback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TJ, TC, TW, TG, TH, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        return this;
    }

    @Override
    public Sql<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> Sql<T, TJ, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}