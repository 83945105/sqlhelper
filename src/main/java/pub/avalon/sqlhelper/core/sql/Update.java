package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.builder.SqlBuilder;

/**
 * 更新
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Update extends Sql {

    /**
     * 使用JavaBean更新
     *
     * @param javaBean
     * @return
     */
    SqlBuilder updateJavaBean(Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return
     */
    SqlBuilder updateJavaBeanSelective(Object javaBean);

}
