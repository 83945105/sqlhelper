package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.builder.SqlBuilder;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface QueryByPrimaryKey extends Sql {

    /**
     * 根据主键查询
     *
     * @param keyValue 主键值
     * @return
     */
    SqlBuilder queryByPrimaryKey(Object keyValue);

}
