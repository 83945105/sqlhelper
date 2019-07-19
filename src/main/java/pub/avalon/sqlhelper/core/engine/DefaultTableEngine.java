package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.DefaultSqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;
import java.util.Set;

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
        if (columnHelpers == null || columnHelpers.length == 0) {
            return this;
        }
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            this.addTableColumnDatum(columnHelper.execute());
        }
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        this.addTableColumnDatum(ColumnCallback.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
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
        this.addTableColumnDatum(ColumnCallback.execute(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> virtualColumn(Object value, String alias) {
        if (alias == null) {
            return this;
        }
        VirtualFieldDatum virtualFieldDatum = new VirtualFieldDatum();
        virtualFieldDatum.setValue(value);
        virtualFieldDatum.setAlias(alias);
        this.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (columnCallback == null) {
            return this;
        }
        TC tc = BeanUtils.tableHelper(this.tableHelperClass).newColumnHelper(this.tableAlias);
        // 设置配置开始
        tc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        tc = columnCallback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableGroupColumnDatum(new TableGroupColumnDatum(this.tableAlias, groupType, columnData));
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
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (columnCallback == null) {
            return this;
        }
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        // 设置配置开始
        sc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        sc = columnCallback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableGroupColumnDatum(new TableGroupColumnDatum(tableAlias, groupType, columnData));
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
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TJ, SJ> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        tableName = tableName == null ? s.getTableName() : tableName;
        tableAlias = tableAlias == null ? s.getTableAlias() : tableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, tableHelperClass, s, tableName, tableAlias);
        this.addJoinTableDatum(joinTableDatum);
        TJ tj = BeanUtils.tableHelper(this.tableHelperClass).newJoinHelper(this.tableAlias);
        // 设置配置开始
        tj.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        OnLinker<TJ, SJ> onLinker = new OnAndOr<>();
        SJ sj = BeanUtils.tableHelper(tableHelperClass).newJoinHelper(tableAlias);
        // 设置配置开始
        sj.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        if (callback == null) {
            return this;
        }
        OnLinker<TJ, SJ> linker = callback.apply(onLinker, sj, tj);
        List<OnDataLinker> onDataLinkers = linker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        joinTableDatum.setTableOnDatum(new TableOnDatum(tableAlias, onDataLinkers));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        if (callback == null) {
            return this;
        }
        TW tw = BeanUtils.tableHelper(this.tableHelperClass).newWhereHelper(this.tableAlias);
        // 设置配置开始
        tw.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        WhereLinker<TW> whereLinker = callback.apply(new WhereAndOr<>(), tw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        this.addTableWhereDatum(new TableWhereDatum(this.tableAlias, whereDataLinkers));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        if (callback == null) {
            return this;
        }
        S s = BeanUtils.tableHelper(tableHelperClass);
        tableAlias = tableAlias == null ? s.getTableAlias() : tableAlias;
        SW sw = s.newWhereHelper(tableAlias);
        // 设置配置开始
        sw.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        TW tw = BeanUtils.tableHelper(this.tableHelperClass).newWhereHelper(this.tableAlias);
        // 设置配置开始
        tw.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        WhereLinker<TW> whereLinker = callback.apply(new WhereAndOr<>(), sw, tw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        this.addTableWhereDatum(new TableWhereDatum(tableAlias, whereDataLinkers));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupCallback<TG> callback) {
        TG tg = BeanUtils.tableHelper(this.tableHelperClass).newGroupHelper(this.tableAlias);
        // 设置配置开始
        tg.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        tg = callback.apply(tg);
        Set<GroupDatum> groupData = tg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum(this.tableAlias, groupData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        tableAlias = tableAlias == null ? s.getTableAlias() : tableAlias;
        SG sg = s.newGroupHelper(tableAlias);
        // 设置配置开始
        sg.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        sg = callback.apply(sg);
        Set<GroupDatum> groupData = sg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum(tableAlias, groupData));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortHelper<?>... sortHelpers) {
        if (sortHelpers == null || sortHelpers.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    @Override
    public DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortCallback<TS> callback) {
        TS ts = BeanUtils.tableHelper(this.tableHelperClass).newSortHelper(this.tableAlias);
        // 设置配置开始
        ts.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        ts = callback.apply(ts);
        Set<SortDatum> sortData = ts.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return this;
        }
        this.addTableSortDatum(new TableSortDatum(this.tableAlias, sortData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        tableAlias = tableAlias == null ? s.getTableAlias() : tableAlias;
        SS ss = s.newSortHelper(tableAlias);
        // 设置配置开始
        ss.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        ss = callback.apply(ss);
        Set<SortDatum> sortData = ss.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return this;
        }
        this.addTableSortDatum(new TableSortDatum(tableAlias, sortData));
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
