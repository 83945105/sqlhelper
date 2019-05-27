package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collection;
import java.util.List;

/**
 * Sql构建代理器
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

    @Override
    public String getSql() {
        return null;
    }

    @Override
    public String getPreparedStatementSql() {
        return null;
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        return null;
    }

    @Override
    public SqlBuilder copyTable(String targetTableName, boolean copyData) {
        this.sqlBuilderTemplate.copyTable()
        return null;
    }

    @Override
    public SqlBuilder deleteTable() {
        return null;
    }

    @Override
    public SqlBuilder renameTable(String newTableName) {
        return null;
    }

    @Override
    public SqlBuilder isTableExist() {
        return null;
    }

    @Override
    public SqlBuilder insertArgs(Object... args) {
        return null;
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        return null;
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        return null;
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return null;
    }

    @Override
    public SqlBuilder delete() {
        return null;
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object primaryKeyValue) {
        return null;
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        return null;
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return null;
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return null;
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return null;
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return null;
    }

    @Override
    public SqlBuilder query() {
        return null;
    }

    @Override
    public SqlBuilder queryCount() {
        return null;
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object primaryKeyValue) {
        return null;
    }
}
