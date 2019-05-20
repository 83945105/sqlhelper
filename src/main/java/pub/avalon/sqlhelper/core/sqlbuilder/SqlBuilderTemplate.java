package pub.avalon.sqlhelper.core.sqlbuilder;

import java.util.Collection;

/**
 * @author 白超
 * @date 2019/5/20
 */
public interface SqlBuilderTemplate<T extends SqlBuilderTemplate<T>> {

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return
     */
    T copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return
     */
    T deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return
     */
    T renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return
     */
    T isTableExist();

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

    /**
     * 删除
     *
     * @return
     */
    T delete();

    /**
     * 根据主键删除
     *
     * @param keyValue 主键值
     * @return
     */
    T deleteByPrimaryKey(Object keyValue);

    /**
     * 根据主键批量删除
     *
     * @param keyValues 主键值集合
     * @return
     */
    T batchDeleteByPrimaryKeys(Collection<?> keyValues);

    /**
     * 使用JavaBean更新
     *
     * @param javaBean
     * @return
     */
    T updateJavaBean(Object javaBean);

    /**
     * 使用JavaBean更新
     * <p>如果值为{@code null}则跳过该属性
     *
     * @param javaBean
     * @return
     */
    T updateJavaBeanSelective(Object javaBean);

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

    /**
     * 查询
     *
     * @return
     */
    T query();

    /**
     * 查询数量
     *
     * @return
     */
    T queryCount();

    /**
     * 根据主键查询
     *
     * @param keyValue 主键值
     * @return
     */
    T queryByPrimaryKey(Object keyValue);

}
