package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface SelectSqlBuilder {

    /**
     * 查询
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult query();

    /**
     * 查询数量
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult queryCount();

    /**
     * 根据主键查询
     *
     * @param primaryKeyValue 主键值
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult queryByPrimaryKey(Object primaryKeyValue);

}
