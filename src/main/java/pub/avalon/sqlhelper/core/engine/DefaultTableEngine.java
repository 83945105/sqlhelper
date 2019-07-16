package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.DefaultSqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 表引擎默认实现
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class DefaultTableEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> implements TableEngine<T, TO, TC, TW, TG, TS, DefaultTableEngine<T, TO, TC, TW, TG, TS>>, DefaultSqlBuilder, SqlDataProducer {

    protected Class<T> tableHelperClass;

    protected T tableHelper;

    protected String tableName;

    protected String tableAlias;

    protected MainTableDatum mainTableDatum;

    private SqlData sqlData;

    protected SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderProxy sqlBuilderProxy;

    public DefaultTableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public DefaultTableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableHelper, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> column(ColumnHelper<?>... columnHelpers) {
        if (columnHelpers == null || columnHelpers.length == 0) {
            return this;
        }
        Set<ColumnDatum> columnData;
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            columnData = columnHelper.takeoutSqlPartData();
            this.addTableColumnDatum(new TableColumnDatum(columnHelper.getTableAlias(), columnData));
        }
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback) {
        if (callback == null) {
            return this;
        }
        TC tc = BeanUtils.tableHelper(this.tableHelperClass).newColumnHelper(this.tableAlias);
        // 设置配置开始
        tc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlPartData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(this.tableHelperClass);
        }
        this.addTableColumnDatum(new TableColumnDatum(this.tableAlias, columnData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (callback == null) {
            return this;
        }
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias = tableAlias == null ? s.getTableAlias() : tableAlias);
        // 设置配置开始
        sc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(tableHelperClass);
        }
        this.addTableColumnDatum(new TableColumnDatum(tableAlias, columnData));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> virtualColumn(Object value, String alias) {
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
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> groupColumn(GroupType groupType, ColumnCallback<TC> callback) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (callback == null) {
            return this;
        }
        TC tc = BeanUtils.tableHelper(this.tableHelperClass).newColumnHelper(this.tableAlias);
        // 设置配置开始
        tc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableFunctionColumnDatum(new TableFunctionColumnDatum(this.tableAlias, groupType, columnData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> callback) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (callback == null) {
            return this;
        }
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        // 设置配置开始
        sc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableFunctionColumnDatum(new TableFunctionColumnDatum(tableAlias, groupType, columnData));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
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
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        tableName = tableName == null ? s.getTableName() : tableName;
        tableAlias = tableAlias == null ? s.getTableAlias() : tableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, tableHelperClass, s, tableName, tableAlias);
        this.addJoinTableDatum(joinTableDatum);
        TO to = BeanUtils.tableHelper(this.tableHelperClass).newOnHelper(this.tableAlias);
        // 设置配置开始
        to.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        OnLinker<TO, SO> onLinker = new OnAndOr<>();
        SO so = BeanUtils.tableHelper(tableHelperClass).newOnHelper(tableAlias);
        // 设置配置开始
        so.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束
        if (callback == null) {
            return this;
        }
        OnLinker<TO, SO> linker = callback.apply(onLinker, so, to);
        List<OnDataLinker> onDataLinkers = linker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        joinTableDatum.setTableOnDatum(new TableOnDatum(tableAlias, onDataLinkers));
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> where(WhereCallback<TW> callback) {
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
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
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
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> group(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
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
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
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
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortHelpers) {
        if (sortHelpers == null || sortHelpers.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback) {
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
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> DefaultTableEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
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
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> limitTop(Long num) {
        this.buildLimitData(1L, num);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> limitOne() {
        this.buildLimitData(1L, 1L);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> limit(LimitSql limit) {
        this.setLimitData(limit);
        return this;
    }

    @Override
    public DefaultTableEngine<T, TO, TC, TW, TG, TS> limit(Long total, Long currentPage, Long pageSize) {
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
    public DefaultSqlBuilder copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilderProxy.copyTable(targetTableName, copyData);
    }

    @Override
    public DefaultSqlBuilder deleteTable() {
        return this.sqlBuilderProxy.deleteTable();
    }

    @Override
    public DefaultSqlBuilder renameTable(String newTableName) {
        return this.sqlBuilderProxy.renameTable(newTableName);
    }

    @Override
    public DefaultSqlBuilder isTableExist() {
        return this.sqlBuilderProxy.isTableExist();
    }

    @Override
    public DefaultSqlBuilder insertArgs(Object... args) {
        return this.sqlBuilderProxy.insertArgs(args);
    }

    @Override
    public DefaultSqlBuilder insertJavaBean(Object javaBean) {
        return this.sqlBuilderProxy.insertJavaBean(javaBean);
    }

    @Override
    public DefaultSqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderProxy.insertJavaBeanSelective(javaBean);
    }

    @Override
    public DefaultSqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public DefaultSqlBuilder delete() {
        return this.sqlBuilderProxy.delete();
    }

    @Override
    public DefaultSqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderProxy.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DefaultSqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlBuilderProxy.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public DefaultSqlBuilder updateJavaBean(Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBean(javaBean);
    }

    @Override
    public DefaultSqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanSelective(javaBean);
    }

    @Override
    public DefaultSqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlBuilderProxy.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public DefaultSqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public DefaultSqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public DefaultSqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public DefaultSqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public DefaultSqlBuilder query() {
        return this.sqlBuilderProxy.query();
    }

    @Override
    public DefaultSqlBuilder queryCount() {
        return this.sqlBuilderProxy.queryCount();
    }

    @Override
    public DefaultSqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
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
    public void setLimitData(LimitSql limitData) {
        this.sqlData.setLimitData(limitData);
    }

    @Override
    public void buildLimitData(Long currentPage, Long pageSize) {
        this.sqlData.buildLimitData(currentPage, pageSize);
    }

    @Override
    public void buildLimitData(Long total, Long currentPage, Long pageSize) {
        this.sqlData.buildLimitData(total, currentPage, pageSize);
    }

    @Override
    public void addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        this.sqlData.addSubQueryData(alias, sqlBuilder);
    }

    @Override
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        this.sqlData.addJoinTableDatum(joinTableDatum);
    }

    @Override
    public void addSubQueryJoinTableDatum(JoinTableDatum joinTableDatum) {
        this.sqlData.addSubQueryJoinTableDatum(joinTableDatum);
    }

}
