package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.*;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
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
public final class SqlHelperEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> implements TableEngine<T, TO, TC, TW, TG, TS, SqlHelperEngine<T, TO, TC, TW, TG, TS>>, SqlBuilder, SqlDataProducer {

    private DefaultTableEngine<T, TO, TC, TW, TG, TS> tableEngine;

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
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> column(ColumnHelper<?>... columnHelpers) {
        this.tableEngine.column(columnHelpers);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback) {
        this.tableEngine.column(callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback) {
        this.tableEngine.column(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> virtualColumn(Object value, String alias) {
        this.tableEngine.virtualColumn(value, alias);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> functionColumn(GroupType groupType, ColumnCallback<TC> callback) {
        this.tableEngine.functionColumn(groupType, callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> callback) {
        this.tableEngine.functionColumn(tableHelperClass, tableAlias, groupType, callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        this.tableEngine.subQuery(tableName, tableHelperClass, alias, callback, columnAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        this.tableEngine.join(joinType, tableName, tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> where(WhereCallback<T, TO, TC, TW, TG, TS> callback) {
        this.tableEngine.where(callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        this.tableEngine.where(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> group(GroupHelper<?>... groupHelpers) {
        this.tableEngine.group(groupHelpers);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
        this.tableEngine.group(callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        this.tableEngine.group(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortHelpers) {
        this.tableEngine.sort(sortHelpers);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback) {
        this.tableEngine.sort(callback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        this.tableEngine.sort(tableHelperClass, tableAlias, callback);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> limitTop(Long num) {
        this.tableEngine.limitTop(num);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> limitOne() {
        this.tableEngine.limitOne();
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> limit(LimitSql limit) {
        this.tableEngine.limit(limit);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TS> limit(Long total, Long currentPage, Long pageSize) {
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
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        return this.tableEngine.copyTable(targetTableName, copyData);
    }

    @Override
    public SqlBuilder deleteTable() {
        return this.tableEngine.deleteTable();
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        return this.tableEngine.renameTable(newTableName);
    }

    @Override
    public SqlBuilder isTableExist() {
        return this.tableEngine.isTableExist();
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        return this.tableEngine.insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        return this.tableEngine.insertJavaBean(javaBean);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.tableEngine.insertJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.tableEngine.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder delete() {
        return this.tableEngine.delete();
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        return this.tableEngine.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.tableEngine.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        return this.tableEngine.updateJavaBean(javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.tableEngine.updateJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.tableEngine.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.tableEngine.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.tableEngine.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.tableEngine.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.tableEngine.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder query() {
        return this.tableEngine.query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.tableEngine.queryCount();
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
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
