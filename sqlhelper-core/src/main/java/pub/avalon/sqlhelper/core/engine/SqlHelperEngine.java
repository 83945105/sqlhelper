package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.data.LimitDatum;
import pub.avalon.sqlhelper.core.engine.builder.*;
import pub.avalon.sqlhelper.core.engine.callback.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class SqlHelperEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>>
        extends AbstractEngine<T, TJ, TC, TW, TG, TH, TS>
        implements JdbcEngine<SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>>, SqlEngine<SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>>, JdbcCallbackEngine<TJ, TC, TW, TG, TH, TS, SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>> {

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
    public <FJ extends JoinHelper<FJ>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlJoin(SqlJoin<FJ> sqlJoin) {
        SqlJoin.execute(sqlJoin, this.sqlBuilderOptions).forEach(this::addJoinTableDatum);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlColumn(SqlColumn<FC> sqlColumn) {
        SqlColumn.execute(sqlColumn, this.sqlBuilderOptions).forEach(this::addTableColumnDatum);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlWhere(SqlWhere<FW> sqlWhere) {
        SqlWhere.execute(sqlWhere, this.sqlBuilderOptions).forEach(this::addTableWhereDatum);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlGroup(SqlGroup<FG> sqlGroup) {
        SqlGroup.execute(sqlGroup, this.sqlBuilderOptions).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlSort(SqlSort<FS> sqlSort) {
        SqlSort.execute(sqlSort, this.sqlBuilderOptions).forEach(this::addTableSortDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeColumn(true, this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
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
        this.addTableColumnDatum(ColumnCallbackEngine.executeColumn(false, tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeVirtualColumn(this.tableHelperClass, this.tableAlias, columnValue, columnAlias));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeGroupColumn(this.tableHelperClass, this.tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
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
        this.addTableColumnDatum(ColumnCallbackEngine.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeSubQueryColumn(this.tableHelperClass, this.tableAlias, columnAlias, subQueryColumnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TJ, SJ> joinCallback) {
        this.addJoinTableDatum(JoinCallbackEngine.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, joinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        WhereHelper.execute(whereHelpers).forEach(this::addTableWhereDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        this.addTableWhereDatum(WhereCallbackEngine.execute(this.tableHelperClass, this.tableAlias, callback, this.sqlBuilderOptions));
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
        this.addTableWhereDatum(WhereCallbackEngine.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, callback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        GroupHelper.execute(groupHelpers).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        this.addTableGroupDatum(GroupCallbackEngine.execute(this.tableHelperClass, this.tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDatum(GroupCallbackEngine.execute(tableHelperClass, tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        SortHelper.execute(sortHelpers).forEach(this::addTableSortDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        this.addTableSortDatum(SortCallbackEngine.execute(this.tableHelperClass, this.tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDatum(SortCallbackEngine.execute(tableHelperClass, tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limitTop(Long num) {
        this.setLimitDatum(new LimitDatum(1L, num));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limitOne() {
        this.setLimitDatum(new LimitDatum(1L, 1L));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limit(LimitSql limit) {
        this.setLimitDatum(new LimitDatum(limit.getTotal(), limit.getCurrentPage(), limit.getPageSize()));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limit(Long total, Long currentPage, Long pageSize) {
        this.setLimitDatum(new LimitDatum(total, currentPage, pageSize));
        return this;
    }
}