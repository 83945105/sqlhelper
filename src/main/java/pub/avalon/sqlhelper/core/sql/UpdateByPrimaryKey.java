package pub.avalon.sqlhelper.core.sql;

import java.util.Collection;

/**
 * 更新
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface UpdateByPrimaryKey<T> extends Sql {

    /**
     * 根据主键更新参数
     *
     * @param keyValue 主键值
     * @param args     参数
     * @return
     */
    T updateArgsByPrimaryKey(Object keyValue, Collection<?> args);

    /**
     * 根据主键,使用JavaBean更新
     *
     * @param keyValue 主键值
     * @param javaBean
     * @return
     */
    T updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean);

    /**
     * 根据主键,使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param keyValue 主键值
     * @param javaBean
     * @return
     */
    T updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean);

    /**
     * 使用JavaBean批量更新
     *
     * @param javaBeans
     * @return
     */
    T batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans);

    /**
     * 使用JavaBean更新或插入
     * <p>存在更新,不存在插入
     *
     * @param javaBeans
     * @return
     */
    T updateOrInsertJavaBeans(Collection<?> javaBeans);
}
