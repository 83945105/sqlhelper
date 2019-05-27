package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.modelbuilder.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;

import java.util.Collection;
import java.util.List;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> implements SqlBuilder<SqlBuilder>, SqlDataProducer<SqlEngine> {

    protected Class<T> tableModelClass;

    private SqlData<T> sqlData;

    private SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderProxy sqlBuilderProxy;

    public SqlEngine(Class<T> tableModelClass) {
        this.tableModelClass = tableModelClass;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableModelClass));
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlEngine(Class<T> tableModelClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableModelClass = tableModelClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableModelClass));
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass) {
        this.tableModelClass = tableModelClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableModelClass = tableModelClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, String alias) {
        this.tableModelClass = tableModelClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, String alias, SqlBuilderOptions sqlBuilderOptions) {
        this.tableModelClass = tableModelClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlData<T> getSqlData() {
        return sqlData;
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
    public SqlEngine setDataBaseType(DataBaseType dataBaseType) {
        this.sqlData.setDataBaseType(dataBaseType);
        return this;
    }

    @Override
    public SqlEngine addTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlEngine addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
        this.sqlData.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    @Override
    public SqlEngine addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum) {
        this.sqlData.addTableFunctionColumnDatum(tableFunctionColumnDatum);
        return this;
    }

    @Override
    public SqlEngine addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    @Override
    public SqlEngine addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        this.sqlData.addTableGroupDatum(tableGroupDatum);
        return this;
    }

    @Override
    public SqlEngine addTableSortDatum(TableSortDatum tableSortDatum) {
        this.sqlData.addTableSortDatum(tableSortDatum);
        return this;
    }

    @Override
    public SqlEngine setLimitData(LimitHandler limitData) {
        this.sqlData.setLimitData(limitData);
        return this;
    }

    @Override
    public SqlEngine buildLimitData(Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(currentPage, pageSize);
        return this;
    }

    @Override
    public SqlEngine buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(total, currentPage, pageSize);
        return this;
    }

    @Override
    public SqlEngine setLimitStart(Integer limitStart) {
        this.sqlData.setLimitStart(limitStart);
        return this;
    }

    @Override
    public SqlEngine setLimitEnd(Integer limitEnd) {
        this.sqlData.setLimitEnd(limitEnd);
        return this;
    }

    @Override
    public SqlEngine addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        this.sqlData.addSubQueryData(alias, sqlBuilder);
        return this;
    }

    @Override
    public <J extends TableModel> SqlEngine addJoinTableData(JoinTableData<J> joinTableData) {
        this.sqlData.addJoinTableData(joinTableData);
        return this;
    }

    @Override
    public <J extends TableModel> SqlEngine addSubQueryJoinTableData(JoinTableData<J> joinTableData) {
        this.sqlData.addSubQueryJoinTableData(joinTableData);
        return this;
    }
}
