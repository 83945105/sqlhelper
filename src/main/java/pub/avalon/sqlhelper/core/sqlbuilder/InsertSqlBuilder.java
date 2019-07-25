package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.Collection;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface InsertSqlBuilder {

    /**
     * 插入参数
     *
     * @param args 参数
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult insertArgs(Object... args);

    /**
     * 使用JavaBean插入
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult insertJavaBean(Object javaBean);

    /**
     * 使用JavaBean插入
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult insertJavaBeanSelective(Object javaBean);

    /**
     * 使用JavaBean批量插入
     *
     * @param javaBeans
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans);

}
