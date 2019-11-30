package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.UpdateSqlBuilderResult;

import java.util.Collection;

/**
 * @author baichao
 */
public interface UpdateSqlBuilder {

    /**
     * update javaBean
     *
     * @param javaBean javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBean(Object javaBean);

    /**
     * update javaBean
     * <p>when value is {@code null},skip field
     *
     * @param javaBean javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean);

    /**
     * update args by primary key
     *
     * @param primaryKeyValue primary key value
     * @param args            args
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args);

    /**
     * update javaBean by primary key
     *
     * @param primaryKeyValue primary key value
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean);

    /**
     * update javaBean by primary key
     * <p>when value is {@code null},skip field
     *
     * @param primaryKeyValue primary key value
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean);

    /**
     * batch update javaBeans by primary keys
     *
     * @param javaBeans javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans);

    /**
     * update or insert javaBeans
     * <p>if exist update else insert
     *
     * @param javaBeans javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans);
}