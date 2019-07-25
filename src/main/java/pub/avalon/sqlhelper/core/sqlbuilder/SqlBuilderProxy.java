package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.*;

import java.util.Collection;

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

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilderTemplate.buildCopyTable(this.sqlData, targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        return this.sqlBuilderTemplate.buildDeleteTable(this.sqlData);
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        return this.sqlBuilderTemplate.buildRenameTable(this.sqlData, newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        return this.sqlBuilderTemplate.buildIsTableExist(this.sqlData);
    }

    @Override
    public InsertSqlBuilderResult insertArgs(Object... args) {
        return this.sqlBuilderTemplate.buildInsertArgs(this.sqlData, args);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        return this.sqlBuilderTemplate.buildInsertJavaBean(this.sqlData, javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderTemplate.buildInsertJavaBeanSelective(this.sqlData, javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderTemplate.buildBatchInsertJavaBeans(this.sqlData, javaBeans);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        return this.sqlBuilderTemplate.buildDelete(this.sqlData);
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderTemplate.buildDeleteByPrimaryKey(this.sqlData, primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlBuilderTemplate.buildBatchDeleteByPrimaryKeys(this.sqlData, primaryKeyValues);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBean(Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBean(this.sqlData, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBeanSelective(this.sqlData, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlBuilderTemplate.buildUpdateArgsByPrimaryKey(this.sqlData, primaryKeyValue, args);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKey(this.sqlData, primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKeySelective(this.sqlData, primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilderTemplate.buildBatchUpdateJavaBeansByPrimaryKeys(this.sqlData, javaBeans);
    }

    @Override
    public UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderTemplate.buildUpdateOrInsertJavaBeans(this.sqlData, javaBeans);
    }

    @Override
    public SelectSqlBuilderResult query() {
        return this.sqlBuilderTemplate.buildQuery(this.sqlData);
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        return this.sqlBuilderTemplate.buildQueryCount(this.sqlData);
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderTemplate.buildQueryByPrimaryKey(this.sqlData, primaryKeyValue);
    }

}
