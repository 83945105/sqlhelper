package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.data.SqlDataConsumer;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;

import java.util.Collection;

/**
 * @author baichao
 */
public interface SqlBuilderTemplate {

    /**
     * set sql part builder template
     *
     * @param sqlPartBuilderTemplate {@link SqlPartBuilderTemplate}
     */
    void setSqlPartBuilderTemplate(SqlPartBuilderTemplate sqlPartBuilderTemplate);

    /**
     * build copy table result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param targetTableName target table name
     * @param copyData        copy data or not
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildCopyTable(SqlDataConsumer sqlDataConsumer, String targetTableName, boolean copyData);

    /**
     * build delete table result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildDeleteTable(SqlDataConsumer sqlDataConsumer);

    /**
     * build rename table result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param newTableName    new table name
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildRenameTable(SqlDataConsumer sqlDataConsumer, String newTableName);

    /**
     * build is table exist result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildIsTableExist(SqlDataConsumer sqlDataConsumer);

    /**
     * build insert args result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param args            args
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertArgs(SqlDataConsumer sqlDataConsumer, Object... args);

    /**
     * build insert javaBean result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBean        javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * build insert javaBean result
     * <p>when value is {@code null},skip field
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBean        javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * build batch insert javaBeans result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBeans       javaBeans
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildBatchInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * build delete result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildDelete(SqlDataConsumer sqlDataConsumer);

    /**
     * build delete by primary key result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param primaryKeyValue primary key value
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildDeleteByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue);

    /**
     * build batch delete by primary keys result
     *
     * @param sqlDataConsumer  {@link SqlDataConsumer}
     * @param primaryKeyValues primary key values
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildBatchDeleteByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Object... primaryKeyValues);

    /**
     * build update javaBean result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * build update javaBean result
     * <p>when value is {@code null},skip field
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * build update args by primary key result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param primaryKeyValue primary key value
     * @param args            args
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateArgsByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object... args);

    /**
     * build update javaBean by primary key result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param primaryKeyValue primary key value
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean);

    /**
     * build update javaBean by primary key result
     * <p>when value is {@code null},skip field
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param primaryKeyValue primary key value
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKeySelective(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean);

    /**
     * build batch update javaBeans by primary keys result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBeans       javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildBatchUpdateJavaBeansByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * build update or insert javaBeans result
     * <p>if exist update else insert
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param javaBeans       javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateOrInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * build query result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQuery(SqlDataConsumer sqlDataConsumer);

    /**
     * build query count result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQueryCount(SqlDataConsumer sqlDataConsumer);

    /**
     * build query by primary key result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @param primaryKeyValue primary key value
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQueryByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue);
}