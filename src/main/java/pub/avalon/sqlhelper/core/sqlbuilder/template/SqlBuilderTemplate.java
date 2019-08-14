package pub.avalon.sqlhelper.core.sqlbuilder.template;

import pub.avalon.sqlhelper.core.data.SqlDataConsumer;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.*;

import java.util.Collection;

/**
 * Sql构建器模板
 *
 * @author baichao
 * @date 2019/5/20
 */
public interface SqlBuilderTemplate {

    /**
     * 设置Sql片段构建器模板
     *
     * @param sqlPartBuilderTemplate {@link SqlPartBuilderTemplate}
     */
    void setSqlPartBuilderTemplate(SqlPartBuilderTemplate sqlPartBuilderTemplate);

    /**
     * 复制表
     *
     * @param sqlDataConsumer sql数据消费者
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildCopyTable(SqlDataConsumer sqlDataConsumer, String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @param sqlDataConsumer sql数据消费者
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildDeleteTable(SqlDataConsumer sqlDataConsumer);

    /**
     * 重命名表
     *
     * @param sqlDataConsumer sql数据消费者
     * @param newTableName    新的表名
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildRenameTable(SqlDataConsumer sqlDataConsumer, String newTableName);

    /**
     * 判断表是否存在
     *
     * @param sqlDataConsumer sql数据消费者
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildIsTableExist(SqlDataConsumer sqlDataConsumer);

    /**
     * 插入参数
     *
     * @param sqlDataConsumer sql数据消费者
     * @param args            参数
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertArgs(SqlDataConsumer sqlDataConsumer, Object... args);

    /**
     * 使用JavaBean插入
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 使用JavaBean插入
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 使用JavaBean批量插入
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBeans
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildBatchInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * 删除
     *
     * @param sqlDataConsumer sql数据消费者
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildDelete(SqlDataConsumer sqlDataConsumer);

    /**
     * 根据主键删除
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildDeleteByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param sqlDataConsumer  sql数据消费者
     * @param primaryKeyValues 主键值集合
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildBatchDeleteByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Object... primaryKeyValues);

    /**
     * 使用JavaBean更新
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 根据主键更新参数
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @param args            参数
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateArgsByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object... args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKeySelective(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildBatchUpdateJavaBeansByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateOrInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * 查询
     *
     * @param sqlDataConsumer sql数据消费者
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQuery(SqlDataConsumer sqlDataConsumer);

    /**
     * 查询数量
     *
     * @param sqlDataConsumer sql数据消费者
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQueryCount(SqlDataConsumer sqlDataConsumer);

    /**
     * 根据主键查询
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQueryByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue);

}
