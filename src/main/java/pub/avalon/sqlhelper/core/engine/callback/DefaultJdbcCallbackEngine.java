package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.engine.AbstractEngine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 回调引擎默认实现
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class DefaultJdbcCallbackEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TJ, TC, TW, TG, TH, TS> implements JdbcCallbackEngine<TJ, TC, TW, TG, TH, TS, DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS>> {

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableHelperClass, tableAlias);
    }

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public DefaultJdbcCallbackEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeColumn(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeColumn(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
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
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        this.addTableColumnDatum(ColumnCallbackEngine.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TJ, SJ> joinCallback) {
        this.addJoinTableDatum(JoinCallbackEngine.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, joinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
        this.addTableWhereDatum(WhereCallbackEngine.execute(this.tableHelperClass, this.tableAlias, whereCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereCallback) {
        this.addTableWhereDatum(WhereCallbackEngine.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, whereCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupCallback<TG> groupCallback) {
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
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDatum(GroupCallbackEngine.execute(tableHelperClass, tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortCallback<TS> sortCallback) {
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
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TJ, TC, TW, TG, TH, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDatum(SortCallbackEngine.execute(tableHelperClass, tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

}