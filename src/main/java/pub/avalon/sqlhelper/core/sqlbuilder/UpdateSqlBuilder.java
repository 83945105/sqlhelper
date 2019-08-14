package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.UpdateSqlBuilderResult;

import java.util.Collection;

/**
 * @author baichao
 * @date 2019/7/23
 */
public interface UpdateSqlBuilder {

    /**
     * 使用JavaBean更新
     *
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBean(Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean);

    /**
     * 根据主键更新参数
     *
     * @param primaryKeyValue 主键值
     * @param args            参数
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param primaryKeyValue 主键值
     * @param javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans);

}
