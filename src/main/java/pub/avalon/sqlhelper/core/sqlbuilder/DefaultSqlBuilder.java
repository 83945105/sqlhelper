package pub.avalon.sqlhelper.core.sqlbuilder;

import java.util.Collection;
import java.util.List;

/**
 * SQL构建器
 *
 * @author 白超
 * @date 2018/8/15
 */
public interface DefaultSqlBuilder extends SqlBuilder {

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder isTableExist();

    /**
     * 插入参数
     *
     * @param args 参数
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder insertArgs(Object... args);

    /**
     * 使用JavaBean插入
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder insertJavaBean(Object javaBean);

    /**
     * 使用JavaBean插入
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder insertJavaBeanSelective(Object javaBean);

    /**
     * 使用JavaBean批量插入
     *
     * @param javaBeans
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder batchInsertJavaBeans(Collection<?> javaBeans);

    /**
     * 删除
     *
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder delete();

    /**
     * 根据主键删除
     *
     * @param primaryKeyValue 主键值
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param primaryKeyValues 主键值集合
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues);

    /**
     * 使用JavaBean更新
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder updateJavaBean(Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder updateJavaBeanSelective(Object javaBean);

    /**
     * 根据主键更新参数
     *
     * @param primaryKeyValue 主键值
     * @param args            参数
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder updateArgsByPrimaryKey(Object primaryKeyValue, Object... args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param javaBeans
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param javaBeans
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans);

    /**
     * 查询
     *
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder query();

    /**
     * 查询数量
     *
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder queryCount();

    /**
     * 根据主键查询
     *
     * @param primaryKeyValue 主键值
     * @return {@link DefaultSqlBuilder}
     */
    DefaultSqlBuilder queryByPrimaryKey(Object primaryKeyValue);

}
