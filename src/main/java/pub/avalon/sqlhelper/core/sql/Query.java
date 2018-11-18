package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.build.SqlBuilder;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Query extends Sql {

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

}
