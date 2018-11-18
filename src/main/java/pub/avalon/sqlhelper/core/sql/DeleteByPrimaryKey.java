package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.build.SqlBuilder;

import java.util.Collection;

/**
 * 删除
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface DeleteByPrimaryKey extends Sql {

    /**
     * 根据主键删除
     *
     * @param keyValue 主键值
     * @return
     */
    SqlBuilder deleteByPrimaryKey(Object keyValue);

    /**
     * 根据主键批量删除
     *
     * @param keyValues 主键值集合
     * @return
     */
    SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues);

}
