package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.InsertSqlBuilderResult;

import java.util.Collection;

/**
 * @author baichao
 */
public interface InsertSqlBuilder {

    /**
     * insert args
     *
     * @param args args
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult insertArgs(Object... args);

    /**
     * insert javaBean
     *
     * @param javaBean javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult insertJavaBean(Object javaBean);

    /**
     * inset javaBean
     * <p>when value is {@code null},skip field
     *
     * @param javaBean javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean);

    /**
     * batch inset javaBeans
     *
     * @param javaBeans javaBeans
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans);
}