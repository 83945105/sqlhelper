package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 表引擎默认实现
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class DefaultTableEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TJ, TC, TW, TG, TH, TS> implements TableEngine<T, TJ, TC, TW, TG, TH, TS, DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS>> {

    public DefaultTableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableHelperClass, tableAlias);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        ColumnEngine.executeColumn(columnHelpers).forEach(this::addTableColumnDatum);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
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
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.addTableColumnDatum(ColumnEngine.executeColumn(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> virtualColumn(Object value, String alias) {
        this.addVirtualFieldDatum(ColumnEngine.executeVirtualColumn(value, alias));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
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
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
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
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias) {
//        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.getSqlData(), tableName, tableHelperClass, alias, callback);
//        this.addSubQueryData(columnAlias, sqlBuilder);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TJ, SJ> joinCallback) {
        this.addJoinTableDatum(JoinEngine.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, joinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
        this.addTableWhereDatum(WhereEngine.execute(this.tableHelperClass, this.tableAlias, whereCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereCallback) {
        this.addTableWhereDatum(WhereEngine.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, whereCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupHelper<?>... groupHelpers) {
        GroupEngine.execute(groupHelpers).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupCallback<TG> groupCallback) {
        this.addTableGroupDatum(GroupEngine.execute(this.tableHelperClass, this.tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDatum(GroupEngine.execute(tableHelperClass, tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortHelper<?>... sortHelpers) {
        SortEngine.execute(sortHelpers).forEach(this::addTableSortDatum);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortCallback<TS> sortCallback) {
        this.addTableSortDatum(SortEngine.execute(this.tableHelperClass, this.tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDatum(SortEngine.execute(tableHelperClass, tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> limitTop(Long num) {
        this.buildLimitData(1L, num);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> limitOne() {
        this.buildLimitData(1L, 1L);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> limit(LimitSql limit) {
        this.setLimitData(limit);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> limit(Long total, Long currentPage, Long pageSize) {
        this.buildLimitData(total, currentPage, pageSize);
        return this;
    }

}
