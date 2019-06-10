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
 * 引擎
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
        TS extends SortHelper<TS>> implements SqlBuilder<SqlBuilder>, SqlDataProducer<SqlHelperEngine<T, TO, TC, TW, TG, TS>> {

    protected Class<T> tableHelperClass;

    private SqlData<T> sqlData;

    private SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderProxy sqlBuilderProxy;

    public SqlHelperEngine(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableHelperClass));
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlHelperEngine(Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableHelperClass));
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, sqlBuilderOptions);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass, String alias) {
        this.tableHelperClass = tableHelperClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS);
    }

    public SqlHelperEngine(String tableName, Class<T> tableHelperClass, String alias, SqlBuilderOptions sqlBuilderOptions) {
        this.tableHelperClass = tableHelperClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableHelperClass);
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
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> setDataBaseType(DataBaseType dataBaseType) {
        this.sqlData.setDataBaseType(dataBaseType);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
        this.sqlData.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum) {
        this.sqlData.addTableFunctionColumnDatum(tableFunctionColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        this.sqlData.addTableGroupDatum(tableGroupDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addTableSortDatum(TableSortDatum tableSortDatum) {
        this.sqlData.addTableSortDatum(tableSortDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> setLimitData(LimitHandler limitData) {
        this.sqlData.setLimitData(limitData);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> buildLimitData(Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(currentPage, pageSize);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(total, currentPage, pageSize);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> setLimitStart(Integer limitStart) {
        this.sqlData.setLimitStart(limitStart);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> setLimitEnd(Integer limitEnd) {
        this.sqlData.setLimitEnd(limitEnd);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        this.sqlData.addSubQueryData(alias, sqlBuilder);
        return this;
    }

    @Override
    public <J extends TableHelper> SqlHelperEngine<T, TO, TC, TW, TG, TS> addJoinTableData(JoinTableData<J> joinTableData) {
        this.sqlData.addJoinTableData(joinTableData);
        return this;
    }

    @Override
    public <J extends TableHelper> SqlHelperEngine<T, TO, TC, TW, TG, TS> addSubQueryJoinTableData(JoinTableData<J> joinTableData) {
        this.sqlData.addSubQueryJoinTableData(joinTableData);
        return this;
    }
}
