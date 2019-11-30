package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.data.SqlData;
import pub.avalonframework.sqlhelper.core.exception.SqlException;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractSqlBuilder implements SqlBuilder {

    private SqlData sqlData;

    private SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderTemplate sqlBuilderTemplate;

    public AbstractSqlBuilder(SqlData sqlData, SqlBuilderOptions sqlBuilderOptions) {
        this.sqlData = sqlData;
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    private void switchSqlBuilderTemplate() {
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                this.sqlBuilderTemplate = this.sqlBuilderOptions.getMySqlBuilderTemplate();
                break;
            default:
                throw new SqlException("SqlBuilder do not support this database type temporarily.");
        }
    }

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildCopyTable(this.sqlData, targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildDeleteTable(this.sqlData);
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildRenameTable(this.sqlData, newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildIsTableExist(this.sqlData);
    }

    @Override
    public InsertSqlBuilderResult insertArgs(Object... args) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildInsertArgs(this.sqlData, args);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildInsertJavaBean(this.sqlData, javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildInsertJavaBeanSelective(this.sqlData, javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildBatchInsertJavaBeans(this.sqlData, javaBeans);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildDelete(this.sqlData);
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildDeleteByPrimaryKey(this.sqlData, primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildBatchDeleteByPrimaryKeys(this.sqlData, primaryKeyValues);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBean(Object javaBean) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildUpdateJavaBean(this.sqlData, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildUpdateJavaBeanSelective(this.sqlData, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildUpdateArgsByPrimaryKey(this.sqlData, primaryKeyValue, args);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKey(this.sqlData, primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKeySelective(this.sqlData, primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildBatchUpdateJavaBeansByPrimaryKeys(this.sqlData, javaBeans);
    }

    @Override
    public UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildUpdateOrInsertJavaBeans(this.sqlData, javaBeans);
    }

    @Override
    public SelectSqlBuilderResult query() {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildQuery(this.sqlData);
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildQueryCount(this.sqlData);
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        this.switchSqlBuilderTemplate();
        return this.sqlBuilderTemplate.buildQueryByPrimaryKey(this.sqlData, primaryKeyValue);
    }
}