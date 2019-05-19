package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.sql.*;

import java.util.Collection;

/**
 * Sql构建代理器
 *
 * @author 白超
 * @date 2018/8/20
 */
public class SqlBuilderProxy implements Table, QueryByPrimaryKey, Query, Insert, UpdateByPrimaryKey, Update, DeleteByPrimaryKey, Delete {

    private SqlBuilder sqlBuilder;

    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    public <T extends TableModel> SqlBuilderProxy(SqlData<T> sqlData) {
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                this.sqlBuilder = new MySqlDynamicBuilder<>(sqlData);
                break;
            case SQLSERVER:
                this.sqlBuilder = new SqlServerDynamicBuilder<>(sqlData);
                break;
            default:
                throw new SqlException("SqlBuilder do not support this database type temporarily.");
        }
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilder.copyTable(targetTableName, copyData);
    }

    @Override
    public SqlBuilder deleteTable() {
        return this.sqlBuilder.deleteTable();
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        return this.sqlBuilder.renameTable(newTableName);
    }

    @Override
    public SqlBuilder isTableExist() {
        return this.sqlBuilder.isTableExist();
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilder.queryByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder query() {
        return this.sqlBuilder.query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilder.queryCount();
    }

    @Override
    public SqlBuilder insertArgs(Collection<?> args) {
        return this.sqlBuilder.insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        return this.sqlBuilder.insertJavaBean(javaBean);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilder.insertJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilder.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Collection<?> args) {
        return this.sqlBuilder.updateArgsByPrimaryKey(keyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean) {
        return this.sqlBuilder.updateJavaBeanByPrimaryKey(keyValue, javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean) {
        return this.sqlBuilder.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean);
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        return this.sqlBuilder.updateJavaBean(javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilder.updateJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilder.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilder.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object keyValue) {
        return this.sqlBuilder.deleteByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return this.sqlBuilder.batchDeleteByPrimaryKeys(keyValues);
    }

    @Override
    public SqlBuilder delete() {
        return this.sqlBuilder.delete();
    }
}
