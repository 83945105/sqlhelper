package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.DefaultSqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/19
 */
public abstract class AbstractEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements Engine, DefaultSqlBuilder, SqlDataProducer {

    protected Class<T> tableHelperClass;

    protected T tableHelper;

    protected String tableName;

    protected String tableAlias;

    protected MainTableDatum mainTableDatum;

    protected SqlData sqlData;

    protected SqlBuilderOptions sqlBuilderOptions;

    protected SqlBuilderProxy sqlBuilderProxy;

    public AbstractEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public AbstractEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public AbstractEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public AbstractEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public AbstractEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
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
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public AbstractEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
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
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
    }

    public AbstractEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
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
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlData = new FinalSqlData(dataBaseType, this.mainTableDatum);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData, this.sqlBuilderOptions);
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
    public void addVirtualColumnDatum(VirtualColumnDatum virtualColumnDatum) {
        this.sqlData.addVirtualColumnDatum(virtualColumnDatum);
    }

    @Override
    public void addTableGroupColumnDatum(TableGroupColumnDatum tableGroupColumnDatum) {
        this.sqlData.addTableGroupColumnDatum(tableGroupColumnDatum);
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
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        this.sqlData.addJoinTableDatum(joinTableDatum);
    }

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
}
