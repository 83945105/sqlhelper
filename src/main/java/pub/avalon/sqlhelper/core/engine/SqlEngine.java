package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.FinalSqlData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.modelbuilder.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderTemplate;

import java.util.Collection;

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
        TS extends SortSqlModel<TS>> implements SqlBuilderTemplate<SqlBuilder> {

    protected Class<T> tableModelClass;

    private SqlData<T> sqlData;

    private SqlBuilderOptions sqlBuilderOptions;

    public SqlEngine(Class<T> tableModelClass) {
        this.tableModelClass = tableModelClass;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableModelClass));
    }

    public SqlEngine(Class<T> tableModelClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableModelClass = tableModelClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableModelClass));
    }

    public SqlEngine(String tableName, Class<T> tableModelClass) {
        this.tableModelClass = tableModelClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, SqlBuilderOptions sqlBuilderOptions) {
        this.tableModelClass = tableModelClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, String alias) {
        this.tableModelClass = tableModelClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(mainTableData);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, String alias, SqlBuilderOptions sqlBuilderOptions) {
        this.tableModelClass = tableModelClass;
        this.sqlBuilderOptions = sqlBuilderOptions;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(mainTableData);
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilderOptions.getSqlBuilder().copyTable(targetTableName, copyData);
    }

    @Override
    public SqlBuilder deleteTable() {
        return this.sqlBuilderOptions.getSqlBuilder().deleteTable();
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        return this.sqlBuilderOptions.getSqlBuilder().renameTable(newTableName);
    }

    @Override
    public SqlBuilder isTableExist() {
        return this.sqlBuilderOptions.getSqlBuilder().isTableExist();
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        return this.sqlBuilderOptions.getSqlBuilder().insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        return this.sqlBuilderOptions.getSqlBuilder().insertJavaBean(javaBean);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderOptions.getSqlBuilder().insertJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderOptions.getSqlBuilder().batchInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder delete() {
        return this.sqlBuilderOptions.getSqlBuilder().delete();
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderOptions.getSqlBuilder().deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlBuilderOptions.getSqlBuilder().batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        return this.sqlBuilderOptions.getSqlBuilder().updateJavaBean(javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderOptions.getSqlBuilder().updateJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlBuilderOptions.getSqlBuilder().updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderOptions.getSqlBuilder().updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderOptions.getSqlBuilder().updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilderOptions.getSqlBuilder().batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderOptions.getSqlBuilder().updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder query() {
        return this.sqlBuilderOptions.getSqlBuilder().query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilderOptions.getSqlBuilder().queryCount();
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderOptions.getSqlBuilder().queryByPrimaryKey(primaryKeyValue);
    }

}
