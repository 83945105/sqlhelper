package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.data.SqlDataProducer;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.DefaultSqlBuilder;

/**
 * SqlHelper引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SqlHelperEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TJ, TC, TW, TG, TH, TS> implements SqlEngine<SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>>, TableEngine<T, TJ, TC, TW, TG, TH, TS, SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>>, DefaultSqlBuilder, SqlDataProducer {

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableHelperClass, tableAlias);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sql(Sql<F, FJ, FC, FW, FG, FH, FS> sql) {
//        this.sqlEngine.sql(sql);
        return this;
    }

    @Override
    public <FJ extends JoinHelper<FJ>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlJoin(SqlJoin<FJ> sqlJoin) {
//        this.sqlEngine.sqlJoin(sqlJoin);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlColumn(SqlColumn<FC> sqlColumn) {
        SqlEngine.execute(sqlColumn, this.sqlBuilderOptions).forEach(this::addTableColumnDatum);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlWhere(SqlWhere<FW> sqlWhere) {
//        this.sqlEngine.sqlWhere(sqlWhere);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlGroup(SqlGroup<FG> sqlGroup) {
//        this.sqlEngine.sqlGroup(sqlGroup);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlSort(SqlSort<FS> sqlSort) {
//        this.sqlEngine.sqlSort(sqlSort);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        ColumnEngine.executeColumn(columnHelpers).forEach(this::addTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        this.addTableColumnDatum(ColumnEngine.executeColumn(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.addTableColumnDatum(ColumnEngine.executeColumn(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> virtualColumn(Object value, String alias) {
        this.addVirtualFieldDatum(ColumnEngine.executeVirtualColumn(value, alias));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        this.addTableGroupColumnDatum(ColumnEngine.executeGroupColumn(this.tableHelperClass, this.tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        this.addTableGroupColumnDatum(ColumnEngine.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias) {
//        this.tableEngine.subQuery(tableName, tableHelperClass, tableAlias, callback, columnAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TJ, SJ> callback) {
        this.addJoinTableDatum(JoinEngine.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        this.addTableWhereDatum(WhereEngine.execute(this.tableHelperClass, this.tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        this.addTableWhereDatum(WhereEngine.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupHelper<?>... groupHelpers) {
        GroupEngine.execute(groupHelpers).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupCallback<TG> callback) {
        this.addTableGroupDatum(GroupEngine.execute(this.tableHelperClass, this.tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        this.addTableGroupDatum(GroupEngine.execute(tableHelperClass, tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortHelper<?>... sortHelpers) {
        SortEngine.execute(sortHelpers).forEach(this::addTableSortDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortCallback<TS> callback) {
        this.addTableSortDatum(SortEngine.execute(this.tableHelperClass, this.tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        this.addTableSortDatum(SortEngine.execute(tableHelperClass, tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limitTop(Long num) {
        this.buildLimitData(1L, num);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limitOne() {
        this.buildLimitData(1L, 1L);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limit(LimitSql limit) {
        this.setLimitData(limit);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limit(Long total, Long currentPage, Long pageSize) {
        this.buildLimitData(total, currentPage, pageSize);
        return this;
    }

}
