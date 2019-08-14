package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 * @date 2019/7/23
 */
public interface SelectSqlBuilder {

    /**
     * 查询
     *
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult query();

    /**
     * 查询数量
     *
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult queryCount();

    /**
     * 根据主键查询
     *
     * @param primaryKeyValue 主键值
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue);

}
