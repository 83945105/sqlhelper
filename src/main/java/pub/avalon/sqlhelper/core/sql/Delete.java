package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.builder.SqlBuilder;

/**
 * 删除
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Delete extends Sql {

    /**
     * 删除
     *
     * @return
     */
    SqlBuilder delete();

}
