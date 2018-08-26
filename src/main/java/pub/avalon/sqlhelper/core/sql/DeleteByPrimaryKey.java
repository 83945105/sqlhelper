package pub.avalon.sqlhelper.core.sql;

import java.util.Collection;

/**
 * 删除
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface DeleteByPrimaryKey<T> extends Sql {

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

}
