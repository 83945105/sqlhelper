package pub.avalon.sqlhelper.core.sql;

import java.util.Collection;

/**
 * 插入
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Insert<T> extends Sql {

    /**
     * 插入参数
     *
     * @param args 参数
     * @return
     */
    T insertArgs(Collection<?> args);

    /**
     * 使用JavaBean插入
     *
     * @param javaBean
     * @return
     */
    T insertJavaBean(Object javaBean);

    /**
     * 使用JavaBean插入
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return
     */
    T insertJavaBeanSelective(Object javaBean);

    /**
     * 使用JavaBean批量插入
     *
     * @param javaBeans
     * @return
     */
    T batchInsertJavaBeans(Collection<?> javaBeans);

}
