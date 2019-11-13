package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalon.sqlhelper.core.data.LimitDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.engine.builder.*;
import pub.avalon.sqlhelper.core.engine.callback.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class SqlHelperEngine<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>>
        extends AbstractEngine<T, TO, TC, TW, TG, TH, TS>
        implements JdbcEngine<SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>>, SqlEngine<SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>>, JdbcCallbackEngine<TO, TC, TW, TG, TH, TS, SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>> {

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
    public <FO extends OnHelper<FO>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> sqlJoin(SqlJoin<FO> sqlJoin) {
        SqlJoin.execute(sqlJoin, this.sqlBuilderOptions).forEach(this::addJoinTableDatum);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> sqlOn(SqlOn<FO> sqlOn) {
        SqlOn.execute(sqlOn, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> sqlColumn(SqlColumn<FC> sqlColumn) {
        SqlColumn.execute(sqlColumn, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> sqlWhere(SqlWhere<FW> sqlWhere) {
        SqlWhere.execute(sqlWhere, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> sqlGroup(SqlGroup<FG> sqlGroup) {
        SqlGroup.execute(sqlGroup, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> sqlSort(SqlSort<FS> sqlSort) {
        SqlSort.execute(sqlSort, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(tableColumnDatum -> {
            this.addSelectTableColumnDatum(tableColumnDatum);
            this.addInsertTableColumnDatum(tableColumnDatum);
            this.addUpdateTableColumnDatum(tableColumnDatum);
        });
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addSelectTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addInsertTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addUpdateTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeColumn(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        this.addInsertTableColumnDatum(tableColumnDatum);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeColumn(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeColumn(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addInsertTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> update(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeColumn(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeColumn(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        this.addInsertTableColumnDatum(tableColumnDatum);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeColumn(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeVirtualColumn(this.tableHelperClass, this.tableAlias, columnValue, columnAlias);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeGroupColumn(this.tableHelperClass, this.tableAlias, groupType, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        TableColumnDatum tableColumnDatum = ColumnCallbackEngine.executeSubQueryColumn(this.tableHelperClass, this.tableAlias, columnAlias, subQueryColumnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addJoinTableDatum(JoinCallbackEngine.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, onJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addTableOnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, onJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        WhereHelper.execute(whereHelpers).forEach(this::addTableWhereDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        this.addTableWhereDatum(WhereCallbackEngine.execute(this.tableHelperClass, this.tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        this.addTableWhereDatum(WhereCallbackEngine.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        GroupHelper.execute(groupHelpers).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        this.addTableGroupDatum(GroupCallbackEngine.execute(this.tableHelperClass, this.tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDatum(GroupCallbackEngine.execute(tableHelperClass, tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        SortHelper.execute(sortHelpers).forEach(this::addTableSortDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        this.addTableSortDatum(SortCallbackEngine.execute(this.tableHelperClass, this.tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDatum(SortCallbackEngine.execute(tableHelperClass, tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> limitTop(Long num) {
        this.setLimitDatum(new LimitDatum(1L, num));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> limitOne() {
        this.setLimitDatum(new LimitDatum(1L, 1L));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> limit(LimitSql limit) {
        this.setLimitDatum(new LimitDatum(limit.getTotal(), limit.getCurrentPage(), limit.getPageSize()));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> limit(Long total, Long currentPage, Long pageSize) {
        this.setLimitDatum(new LimitDatum(total, currentPage, pageSize));
        return this;
    }
}