package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.SqlDataConsumer;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;

import java.util.Collection;
import java.util.Map;

/**
 * @author 白超
 * @date 2019/5/20
 */
public interface SqlBuilderTemplate<T> {

    /**
     * 复制表
     *
     * @param sqlDataConsumer sql数据消费者
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return T
     */
    T copyTable(SqlDataConsumer sqlDataConsumer, String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @param sqlDataConsumer sql数据消费者
     * @return T
     */
    T deleteTable(SqlDataConsumer sqlDataConsumer);

    /**
     * 重命名表
     *
     * @param sqlDataConsumer sql数据消费者
     * @param newTableName    新的表名
     * @return T
     */
    T renameTable(SqlDataConsumer sqlDataConsumer, String newTableName);

    /**
     * 判断表是否存在
     *
     * @param sqlDataConsumer sql数据消费者
     * @return T
     */
    T isTableExist(SqlDataConsumer sqlDataConsumer);

    /**
     * 插入参数
     *
     * @param sqlDataConsumer sql数据消费者
     * @param args            参数
     * @return T
     */
    T insertArgs(SqlDataConsumer sqlDataConsumer, Object... args);

    /**
     * 使用JavaBean插入
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return T
     */
    T insertJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 使用JavaBean插入
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return T
     */
    T insertJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 使用JavaBean批量插入
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBeans
     * @return T
     */
    T batchInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * 删除
     *
     * @param sqlDataConsumer sql数据消费者
     * @return T
     */
    T delete(SqlDataConsumer sqlDataConsumer);

    /**
     * 根据主键删除
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @return T
     */
    T deleteByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param sqlDataConsumer  sql数据消费者
     * @param primaryKeyValues 主键值集合
     * @return T
     */
    T batchDeleteByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Object... primaryKeyValues);

    /**
     * 使用JavaBean更新
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return T
     */
    T updateJavaBean(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBean
     * @return T
     */
    T updateJavaBeanSelective(SqlDataConsumer sqlDataConsumer, Object javaBean);

    /**
     * 根据主键更新参数
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @param args            参数
     * @return T
     */
    T updateArgsByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object... args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return T
     */
    T updateJavaBeanByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return T
     */
    T updateJavaBeanByPrimaryKeySelective(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param sqlDataConsumer       sql数据消费者
     * @param javaBeans
     * @return
     */
    T batchUpdateJavaBeansByPrimaryKeys(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param sqlDataConsumer sql数据消费者
     * @param javaBeans
     * @return T
     */
    T updateOrInsertJavaBeans(SqlDataConsumer sqlDataConsumer, Collection<?> javaBeans);

    /**
     * 查询
     *
     * @param sqlDataConsumer sql数据消费者
     * @return T
     */
    T query(SqlDataConsumer sqlDataConsumer);

    /**
     * 查询数量
     *
     * @param sqlDataConsumer sql数据消费者
     * @return T
     */
    T queryCount(SqlDataConsumer sqlDataConsumer);

    /**
     * 根据主键查询
     *
     * @param sqlDataConsumer sql数据消费者
     * @param primaryKeyValue 主键值
     * @return T
     */
    T queryByPrimaryKey(SqlDataConsumer sqlDataConsumer, Object primaryKeyValue);

}
