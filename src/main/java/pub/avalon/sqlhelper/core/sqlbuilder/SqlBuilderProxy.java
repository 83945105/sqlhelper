package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collection;
import java.util.List;

/**
 * Sql构建代理
 *
 * @author 白超
 * @date 2018/8/20
 */
public class SqlBuilderProxy implements SqlBuilder<SqlBuilder> {

    private SqlData<?> sqlData;

    private SqlBuilderTemplate<SqlBuilderResult> sqlBuilderTemplate;

    public <T extends TableModel> SqlBuilderProxy(SqlData<T> sqlData, SqlBuilderOptions sqlBuilderOptions) {
        this.sqlData = sqlData;
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                this.sqlBuilderTemplate = sqlBuilderOptions.getMySqlBuilderTemplate();
                break;
            default:
                throw new SqlException("SqlBuilder do not support this database type temporarily.");
        }
    }

    private SqlBuilderResult sqlBuilderResult;

    @Override
    public String getSql() {
        return null;
    }

    @Override
    public String getPreparedStatementSql() {
        return this.sqlBuilderResult.getPreparedStatementSql();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        return this.sqlBuilderResult.getPreparedStatementArgs();
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.copyTable(this.sqlData, targetTableName, copyData);
        return this;
    }

    @Override
    public SqlBuilder deleteTable() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.deleteTable(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.renameTable(this.sqlData, newTableName);
        return this;
    }

    @Override
    public SqlBuilder isTableExist() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.isTableExist(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.insertArgs(this.sqlData, args);
        return this;
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.insertJavaBean(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.insertJavaBeanSelective(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.batchInsertJavaBeans(this.sqlData, javaBeans);
        return this;
    }

    @Override
    public SqlBuilder delete() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.delete(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.deleteByPrimaryKey(this.sqlData, primaryKeyValue);
        return this;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.batchDeleteByPrimaryKeys(this.sqlData, primaryKeyValues);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.updateJavaBean(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.updateJavaBeanSelective(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.updateArgsByPrimaryKey(this.sqlData, primaryKeyValue, args);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.updateJavaBeanByPrimaryKey(this.sqlData, primaryKeyValue, javaBean);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.updateJavaBeanByPrimaryKeySelective(this.sqlData, primaryKeyValue, javaBean);
        return this;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.batchUpdateJavaBeansByPrimaryKeys(this.sqlData, javaBeans);
        return this;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.updateOrInsertJavaBeans(this.sqlData, javaBeans);
        return this;
    }

    @Override
    public SqlBuilder query() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.query(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder queryCount() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.queryCount(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.queryByPrimaryKey(this.sqlData, primaryKeyValue);
        return this;
    }

}
