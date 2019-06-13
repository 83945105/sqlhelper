package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;

import java.util.Collection;
import java.util.List;

/**
 * SqlHelper引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlHelperEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> implements SqlBuilder<SqlBuilder>, SqlDataProducer {

    protected Class<T> tableHelperClass;

    protected String tableAlias;

    private SqlData<T> sqlData;

    private SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderProxy sqlBuilderProxy;

    public SqlHelperEngine(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlHelperEngine(Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        this.tableAlias = mainTableData.getTableAlias();
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass, String tableAlias) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(tableAlias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(tableAlias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
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
