package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.builder.SqlBuilder;

/**
 * @author 白超
 * @date 2019/5/19
 */
public interface SelectMethods {

    /**
     * 查询
     *
     * @return
     */
    SqlBuilder query();

    /**
     * 查询数量
     *
     * @return
     */
    SqlBuilder queryCount();

    /**
     * 根据主键查询
     *
     * @param keyValue 主键值
     * @return
     */
    SqlBuilder queryByPrimaryKey(Object keyValue);

}
