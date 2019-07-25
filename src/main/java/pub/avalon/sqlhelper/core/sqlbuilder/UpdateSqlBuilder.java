package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.Collection;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface UpdateSqlBuilder {

    /**
     * 使用JavaBean更新
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult updateJavaBean(Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult updateJavaBeanSelective(Object javaBean);

    /**
     * 根据主键更新参数
     *
     * @param primaryKeyValue 主键值
     * @param args            参数
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param javaBeans
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param javaBeans
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans);

}
