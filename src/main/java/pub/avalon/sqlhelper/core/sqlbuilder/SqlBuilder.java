package pub.avalon.sqlhelper.core.sqlbuilder;

import java.util.Collection;
import java.util.List;

/**
 * SQL构建器
 *
 * @author 白超
 * @date 2018/8/15
 */
public interface SqlBuilder {

    /**
     * 获取sql
     * 该sql为填充过参数的sql语句
     *
     * @return sql
     */
    String getSql();

    /**
     * 获取预编译SQL
     *
     * @return sql
     */
    String getPreparedStatementSql();

    /**
     * 获取预编译参数
     *
     * @return 参数
     */
    List<Object> getPreparedStatementArgs();

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return {@link SqlBuilder}
     */
    SqlBuilder copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return {@link SqlBuilder}
     */
    SqlBuilder deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return {@link SqlBuilder}
     */
    SqlBuilder renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return {@link SqlBuilder}
     */
    SqlBuilder isTableExist();

    /**
     * 插入参数
     *
     * @param args 参数
     * @return {@link SqlBuilder}
     */
    SqlBuilder insertArgs(Object... args);

    /**
     * 使用JavaBean插入
     *
     * @param javaBean
     * @return {@link SqlBuilder}
     */
    SqlBuilder insertJavaBean(Object javaBean);

    /**
     * 使用JavaBean插入
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link SqlBuilder}
     */
    SqlBuilder insertJavaBeanSelective(Object javaBean);

    /**
     * 使用JavaBean批量插入
     *
     * @param javaBeans
     * @return {@link SqlBuilder}
     */
    SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans);

    /**
     * 删除
     *
     * @return {@link SqlBuilder}
     */
    SqlBuilder delete();

    /**
     * 根据主键删除
     *
     * @param primaryKeyValue 主键值
     * @return {@link SqlBuilder}
     */
    SqlBuilder deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param primaryKeyValues 主键值集合
     * @return {@link SqlBuilder}
     */
    SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues);

    /**
     * 使用JavaBean更新
     *
     * @param javaBean
     * @return {@link SqlBuilder}
     */
    SqlBuilder updateJavaBean(Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link SqlBuilder}
     */
    SqlBuilder updateJavaBeanSelective(Object javaBean);

    /**
     * 根据主键更新参数
     *
     * @param primaryKeyValue 主键值
     * @param args            参数
     * @return {@link SqlBuilder}
     */
    SqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link SqlBuilder}
     */
    SqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link SqlBuilder}
     */
    SqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param javaBeans
     * @return {@link SqlBuilder}
     */
    SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param javaBeans
     * @return {@link SqlBuilder}
     */
    SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans);

    /**
     * 查询
     *
     * @return {@link SqlBuilder}
     */
    SqlBuilder query();

    /**
     * 查询数量
     *
     * @return {@link SqlBuilder}
     */
    SqlBuilder queryCount();

    /**
     * 根据主键查询
     *
     * @param primaryKeyValue 主键值
     * @return {@link SqlBuilder}
     */
    SqlBuilder queryByPrimaryKey(Object primaryKeyValue);

}
