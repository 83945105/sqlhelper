package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * SqlHelper引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SqlHelperEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> implements TableEngine<T, TO, TC, TW, TG, TS>, SqlBuilder, SqlDataProducer {

    protected Class<T> tableHelperClass;

    protected String tableAlias;

    private SqlData<T> sqlData;

    protected SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderProxy sqlBuilderProxy;

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlData.setDataBaseType(dataBaseType);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlData.setDataBaseType(dataBaseType);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlData.setDataBaseType(dataBaseType);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlData.setDataBaseType(dataBaseType);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(tableAlias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlData.setDataBaseType(dataBaseType);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(tableAlias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlData.setDataBaseType(dataBaseType);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> column(ColumnHelper<?>... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback) {
        T t = BeanUtils.tableHelper(this.tableHelperClass);
        TC tc = t.newColumnHelper();
        tc.setSqlBuilderOptions(this.sqlBuilderOptions);
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlPartData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(t);
        }
        this.addTableColumnDatum(new TableColumnDatum<>(this.tableHelperClass, this.tableAlias, columnData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper();
        sc.setSqlBuilderOptions(this.sqlBuilderOptions);
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(s);
        }
        this.addTableColumnDatum(new TableColumnDatum<>(tableHelperClass, tableAlias, columnData));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> virtualColumn(Object value, String alias) {
        VirtualFieldDatum virtualFieldDatum = new VirtualFieldDatum();
        virtualFieldDatum.setValue(value);
        virtualFieldDatum.setAlias(alias);
        this.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> functionColumn(GroupType groupType, ColumnCallback<TC> callback) {
        if (groupType == null) {
            return this;
        }
        TC tc = BeanUtils.tableHelper(this.tableHelperClass).newColumnHelper();
        tc.setSqlBuilderOptions(this.sqlBuilderOptions);
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableFunctionColumnDatum(new TableFunctionColumnDatum<>(this.tableHelperClass, this.tableAlias, groupType, columnData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> callback) {
        if (groupType == null) {
            return this;
        }
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc.setSqlBuilderOptions(this.sqlBuilderOptions);
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableFunctionColumnDatum(new TableFunctionColumnDatum<>(tableHelperClass, tableAlias, groupType, columnData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
//        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.getSqlData(), tableName, tableHelperClass, alias, callback);
//        this.addSubQueryData(columnAlias, sqlBuilder);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        JoinTableData<S> joinTableData = new JoinTableData<>(tableHelperClass);
        joinTableData.setTableName(tableName);
        joinTableData.setTableAlias(tableAlias);
        joinTableData.setJoinType(joinType);
        this.addJoinTableData(joinTableData);
        TO to = BeanUtils.tableHelper(this.tableHelperClass).newOnHelper();
        to.setSqlBuilderOptions(this.sqlBuilderOptions);
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = new OnAndOr<>();
        SO so = BeanUtils.tableHelper(tableHelperClass).newOnHelper();
        so.setSqlBuilderOptions(this.sqlBuilderOptions);
        if (callback == null) {
            return this;
        }
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> linker = callback.apply(onLinker, so, to);
        List<OnDataLinker> onDataLinkers = linker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        joinTableData.setTableOnDatum(new TableOnDatum<>(tableHelperClass, tableAlias, onDataLinkers));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> where(WhereCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return this;
        }
        TW tw = BeanUtils.tableHelper(this.tableHelperClass).newWhereHelper();
        tw.setSqlBuilderOptions(this.sqlBuilderOptions);
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereAndOr<>(), tw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        this.addTableWhereDatum(new TableWhereDatum<>(this.tableHelperClass, this.tableAlias, whereDataLinkers));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        if (callback == null) {
            return this;
        }
        SW sw = BeanUtils.tableHelper(tableHelperClass).newWhereHelper();
        sw.setSqlBuilderOptions(this.sqlBuilderOptions);
        TW tw = BeanUtils.tableHelper(this.tableHelperClass).newWhereHelper();
        tw.setSqlBuilderOptions(this.sqlBuilderOptions);
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereAndOr<>(), sw, tw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        this.addTableWhereDatum(new TableWhereDatum<>(tableHelperClass, tableAlias, whereDataLinkers));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> group(GroupHelper<?>... groupSqlModels) {
        if (groupSqlModels == null || groupSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
        TG tg = BeanUtils.tableHelper(this.tableHelperClass).newGroupHelper();
        tg.setSqlBuilderOptions(this.sqlBuilderOptions);
        tg = callback.apply(tg);
        Set<GroupDatum> groupData = tg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum<>(this.tableHelperClass, this.tableAlias, groupData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        SG sg = BeanUtils.tableHelper(tableHelperClass).newGroupHelper();
        sg.setSqlBuilderOptions(this.sqlBuilderOptions);
        sg = callback.apply(sg);
        Set<GroupDatum> groupData = sg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum<>(tableHelperClass, tableAlias, groupData));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortSqlModels) {
        if (sortSqlModels == null || sortSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback) {
        TS ts = BeanUtils.tableHelper(this.tableHelperClass).newSortHelper();
        ts.setSqlBuilderOptions(this.sqlBuilderOptions);
        ts = callback.apply(ts);
        Set<SortDatum> sortData = ts.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return this;
        }
        this.addTableSortDatum(new TableSortDatum<>(this.tableHelperClass, this.tableAlias, sortData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        SS ss = BeanUtils.tableHelper(tableHelperClass).newSortHelper();
        ss.setSqlBuilderOptions(this.sqlBuilderOptions);
        ss = callback.apply(ss);
        Set<SortDatum> sortData = ss.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return this;
        }
        this.addTableSortDatum(new TableSortDatum<>(tableHelperClass, tableAlias, sortData));
        return this;
    }

    @Override
    public SqlHelperEngine limitTop(Integer num) {
        this.buildLimitData(1, num);
        return this;
    }

    @Override
    public SqlHelperEngine limitOne() {
        this.buildLimitData(1, 1);
        return this;
    }

    @Override
    public SqlHelperEngine limit(LimitHandler limit) {
        this.setLimitData(limit);
        return this;
    }

    @Override
    public SqlHelperEngine limit(Integer start, Integer end) {
        this.setLimitStart(start);
        this.setLimitEnd(end);
        return this;
    }

    @Override
    public SqlHelperEngine limit(Integer total, Integer currentPage, Integer pageSize) {
        this.buildLimitData(total, currentPage, pageSize);
        return this;
    }

    @Override
    public String getSql() {
        return this.sqlBuilderProxy.getSql();
    }

    @Override
    public String getPreparedStatementSql() {
        return this.sqlBuilderProxy.getPreparedStatementSql();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        return this.sqlBuilderProxy.getPreparedStatementArgs();
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilderProxy.copyTable(targetTableName, copyData);
    }

    @Override
    public SqlBuilder deleteTable() {
        return this.sqlBuilderProxy.deleteTable();
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        return this.sqlBuilderProxy.renameTable(newTableName);
    }

    @Override
    public SqlBuilder isTableExist() {
        return this.sqlBuilderProxy.isTableExist();
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        return this.sqlBuilderProxy.insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        return this.sqlBuilderProxy.insertJavaBean(javaBean);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderProxy.insertJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder delete() {
        return this.sqlBuilderProxy.delete();
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderProxy.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlBuilderProxy.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBean(javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlBuilderProxy.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder query() {
        return this.sqlBuilderProxy.query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilderProxy.queryCount();
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderProxy.queryByPrimaryKey(primaryKeyValue);
    }

    @Override
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.sqlData.setDataBaseType(dataBaseType);
    }

    @Override
    public void addTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addTableColumnDatum(tableColumnDatum);
    }

    @Override
    public void addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
        this.sqlData.addVirtualFieldDatum(virtualFieldDatum);
    }

    @Override
    public void addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum) {
        this.sqlData.addTableFunctionColumnDatum(tableFunctionColumnDatum);
    }

    @Override
    public void addTableWhereDatum(TableWhereDatum tableWhereDatum) {
        this.sqlData.addTableWhereDatum(tableWhereDatum);
    }

    @Override
    public void addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        this.sqlData.addTableGroupDatum(tableGroupDatum);
    }

    @Override
    public void addTableSortDatum(TableSortDatum tableSortDatum) {
        this.sqlData.addTableSortDatum(tableSortDatum);
    }

    @Override
    public void setLimitData(LimitHandler limitData) {
        this.sqlData.setLimitData(limitData);
    }

    @Override
    public void buildLimitData(Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(currentPage, pageSize);
    }

    @Override
    public void buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(total, currentPage, pageSize);
    }

    @Override
    public void setLimitStart(Integer limitStart) {
        this.sqlData.setLimitStart(limitStart);
    }

    @Override
    public void setLimitEnd(Integer limitEnd) {
        this.sqlData.setLimitEnd(limitEnd);
    }

    @Override
    public void addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        this.sqlData.addSubQueryData(alias, sqlBuilder);
    }

    @Override
    public <J extends TableHelper> void addJoinTableData(JoinTableData<J> joinTableData) {
        this.sqlData.addJoinTableData(joinTableData);
    }

    @Override
    public <J extends TableHelper> void addSubQueryJoinTableData(JoinTableData<J> joinTableData) {
        this.sqlData.addSubQueryJoinTableData(joinTableData);
    }

}
