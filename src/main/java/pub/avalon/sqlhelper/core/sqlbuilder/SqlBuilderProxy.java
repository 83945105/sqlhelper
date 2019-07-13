package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collection;
import java.util.List;

/**
 * Sql构建代理
 *
 * @author 白超
 * @date 2018/8/20
 */
public class SqlBuilderProxy implements SqlBuilder {

    private SqlData sqlData;

    private SqlBuilderTemplate<SqlBuilderResult> sqlBuilderTemplate;

    public SqlBuilderProxy(SqlData sqlData, SqlBuilderOptions sqlBuilderOptions) {
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
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildCopyTable(this.sqlData, targetTableName, copyData);
        return this;
    }

    @Override
    public SqlBuilder deleteTable() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildDeleteTable(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildRenameTable(this.sqlData, newTableName);
        return this;
    }

    @Override
    public SqlBuilder isTableExist() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildIsTableExist(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildInsertArgs(this.sqlData, args);
        return this;
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildInsertJavaBean(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildInsertJavaBeanSelective(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildBatchInsertJavaBeans(this.sqlData, javaBeans);
        return this;
    }

    @Override
    public SqlBuilder delete() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildDelete(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildDeleteByPrimaryKey(this.sqlData, primaryKeyValue);
        return this;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildBatchDeleteByPrimaryKeys(this.sqlData, primaryKeyValues);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildUpdateJavaBean(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildUpdateJavaBeanSelective(this.sqlData, javaBean);
        return this;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildUpdateArgsByPrimaryKey(this.sqlData, primaryKeyValue, args);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKey(this.sqlData, primaryKeyValue, javaBean);
        return this;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKeySelective(this.sqlData, primaryKeyValue, javaBean);
        return this;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildBatchUpdateJavaBeansByPrimaryKeys(this.sqlData, javaBeans);
        return this;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildUpdateOrInsertJavaBeans(this.sqlData, javaBeans);
        return this;
    }

    @Override
    public SqlBuilder query() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildQuery(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder queryCount() {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildQueryCount(this.sqlData);
        return this;
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        this.sqlBuilderResult = this.sqlBuilderTemplate.buildQueryByPrimaryKey(this.sqlData, primaryKeyValue);
        return this;
    }

}
