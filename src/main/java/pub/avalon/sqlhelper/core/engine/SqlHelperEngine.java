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

import java.util.Collection;
import java.util.List;

/**
 * SqlHelper引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SqlHelperEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements SqlEngine<SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>>, TableEngine<T, TJ, TC, TW, TG, TH, TS, SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS>>, DefaultSqlBuilder, SqlDataProducer {

    private DefaultTableEngine<T, TJ, TC, TW, TG, TH, TS> tableEngine;

    /*@Override
    public String getSql() {
        //TODO 待实现 通过正则表达式将预编译参数替换进预编译sql
        return null;
    }

    @Override
    public String getPreparedStatementSql() {
        String sql = this.preparedStatementSql.toString();
        if (this.sqlEnabled && this.logger.isDebugEnabled()) {
            if (this.colour) {
                logger.debug("sqlhelper PreparedStatementSQL  [" + sql + "]");
            } else {
                logger.debug(Ansi.ansi().eraseScreen()
                        .fg(Ansi.Color.CYAN)
                        .a("sqlhelper ")
                        .fg(Ansi.Color.YELLOW)
                        .a("PreparedStatementSQL  [" + sql + "]")
                        .reset());
            }
        }
        return sql;
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        if (this.argsEnabled && this.logger.isDebugEnabled()) {
            if (this.colour) {
                logger.debug("sqlhelper PreparedStatementArgs " + this.preparedStatementArgs.toString());
            } else {
                logger.debug(Ansi.ansi().eraseScreen()
                        .fg(Ansi.Color.CYAN)
                        .a("sqlhelper ")
                        .fg(Ansi.Color.RED)
                        .a("PreparedStatementArgs " + this.preparedStatementArgs.toString())
                        .reset());
            }
        }
        return this.preparedStatementArgs;
    }*/

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableHelperClass);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableName, tableHelperClass);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableHelperClass, tableAlias);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public SqlHelperEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        this.tableEngine = new DefaultTableEngine<>(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sql(Sql<F, FJ, FC, FW, FG, FH, FS> sql) {
        // 暂未实现
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlColumn(SqlColumn<FC> sqlColumn) {
        // 暂未实现
        if (sqlColumn == null) {
            return this;
        }
        List<SqlColumnBean<FC>> sqlColumnBeans = sqlColumn.getSqlColumnBeans();
        for (SqlColumnBean<FC> sqlColumnBean : sqlColumnBeans) {
            if (sqlColumnBean instanceof SqlColumnBeanJoin) {
                this.column(((SqlColumnBeanJoin) sqlColumnBean).getTableHelperClass(), ((SqlColumnBeanJoin) sqlColumnBean).getTableAlias(), ((SqlColumnBeanJoin) sqlColumnBean).getColumnCallbackJoin());
                continue;
            }
            this.column((ColumnCallback<TC>) sqlColumnBean.getColumnCallback());
        }
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlWhere(SqlWhere<FW> sqlWhere) {
        // 暂未实现
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlGroup(SqlGroup<FG> sqlGroup) {
        // 暂未实现
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlSort(SqlSort<FS> sqlSort) {
        // 暂未实现
        return this;
    }

    @Override
    public <FJ extends JoinHelper<FJ>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sqlJoin(SqlJoin<FJ> sqlJoin) {
        // 暂未实现
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnHelper<?>... columnHelpers) {
        this.tableEngine.column(columnHelpers);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        this.tableEngine.column(columnCallback);
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
        this.tableEngine.column(tableHelperClass, tableAlias, columnCallback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> virtualColumn(Object value, String alias) {
        this.tableEngine.virtualColumn(value, alias);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        this.tableEngine.groupColumn(groupType, columnCallback);
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
        this.tableEngine.groupColumn(tableHelperClass, tableAlias, groupType, columnCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SJ, SC, SW, SG, SH, SS> callback, String columnAlias) {
        this.tableEngine.subQuery(tableName, tableHelperClass, tableAlias, callback, columnAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TJ, SJ> callback) {
        this.tableEngine.join(joinType, tableName, tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> where(WhereCallback<TW> callback) {
        this.tableEngine.where(callback);
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
        this.tableEngine.where(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupHelper<?>... groupHelpers) {
        this.tableEngine.group(groupHelpers);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> group(GroupCallback<TG> callback) {
        this.tableEngine.group(callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        this.tableEngine.group(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortHelper<?>... sortHelpers) {
        this.tableEngine.sort(sortHelpers);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sort(SortCallback<TS> callback) {
        this.tableEngine.sort(callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        this.tableEngine.sort(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limitTop(Long num) {
        this.tableEngine.limitTop(num);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limitOne() {
        this.tableEngine.limitOne();
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limit(LimitSql limit) {
        this.tableEngine.limit(limit);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> limit(Long total, Long currentPage, Long pageSize) {
        this.tableEngine.limit(total, currentPage, pageSize);
        return this;
    }

    @Override
    public String getSql() {
        return this.tableEngine.getSql();
    }

    @Override
    public String getPreparedStatementSql() {
        return this.tableEngine.getPreparedStatementSql();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        return this.tableEngine.getPreparedStatementArgs();
    }

    @Override
    public DefaultSqlBuilder copyTable(String targetTableName, boolean copyData) {
        return this.tableEngine.copyTable(targetTableName, copyData);
    }

    @Override
    public DefaultSqlBuilder deleteTable() {
        return this.tableEngine.deleteTable();
    }

    @Override
    public DefaultSqlBuilder renameTable(String newTableName) {
        return this.tableEngine.renameTable(newTableName);
    }

    @Override
    public DefaultSqlBuilder isTableExist() {
        return this.tableEngine.isTableExist();
    }

    @Override
    public DefaultSqlBuilder insertArgs(Object... args) {
        return this.tableEngine.insertArgs(args);
    }

    @Override
    public DefaultSqlBuilder insertJavaBean(Object javaBean) {
        return this.tableEngine.insertJavaBean(javaBean);
    }

    @Override
    public DefaultSqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.tableEngine.insertJavaBeanSelective(javaBean);
    }

    @Override
    public DefaultSqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.tableEngine.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public DefaultSqlBuilder delete() {
        return this.tableEngine.delete();
    }

    @Override
    public DefaultSqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        return this.tableEngine.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DefaultSqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.tableEngine.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public DefaultSqlBuilder updateJavaBean(Object javaBean) {
        return this.tableEngine.updateJavaBean(javaBean);
    }

    @Override
    public DefaultSqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.tableEngine.updateJavaBeanSelective(javaBean);
    }

    @Override
    public DefaultSqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.tableEngine.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public DefaultSqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.tableEngine.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public DefaultSqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.tableEngine.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public DefaultSqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.tableEngine.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public DefaultSqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.tableEngine.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public DefaultSqlBuilder query() {
        return this.tableEngine.query();
    }

    @Override
    public DefaultSqlBuilder queryCount() {
        return this.tableEngine.queryCount();
    }

    @Override
    public DefaultSqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        return this.tableEngine.queryByPrimaryKey(primaryKeyValue);
    }

    @Override
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.tableEngine.setDataBaseType(dataBaseType);
    }

    @Override
    public void addTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.tableEngine.addTableColumnDatum(tableColumnDatum);
    }

    @Override
    public void addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
        this.tableEngine.addVirtualFieldDatum(virtualFieldDatum);
    }

    @Override
    public void addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum) {
        this.tableEngine.addTableFunctionColumnDatum(tableFunctionColumnDatum);
    }

    @Override
    public void addTableWhereDatum(TableWhereDatum tableWhereDatum) {
        this.tableEngine.addTableWhereDatum(tableWhereDatum);
    }

    @Override
    public void addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        this.tableEngine.addTableGroupDatum(tableGroupDatum);
    }

    @Override
    public void addTableSortDatum(TableSortDatum tableSortDatum) {
        this.tableEngine.addTableSortDatum(tableSortDatum);
    }

    @Override
    public void setLimitData(LimitSql limitData) {
        this.tableEngine.setLimitData(limitData);
    }

    @Override
    public void buildLimitData(Long currentPage, Long pageSize) {
        this.tableEngine.buildLimitData(currentPage, pageSize);
    }

    @Override
    public void buildLimitData(Long total, Long currentPage, Long pageSize) {
        this.tableEngine.buildLimitData(total, currentPage, pageSize);
    }

    @Override
    public void addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        this.tableEngine.addSubQueryData(alias, sqlBuilder);
    }

    @Override
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        this.tableEngine.addJoinTableDatum(joinTableDatum);
    }

    @Override
    public void addSubQueryJoinTableDatum(JoinTableDatum joinTableDatum) {
        this.tableEngine.addSubQueryJoinTableDatum(joinTableDatum);
    }

}
