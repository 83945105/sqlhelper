package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
public interface SelectSqlBuilder {

    /**
     * query
     *
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult query();

    /**
     * query count
     *
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult queryCount();

    /**
     * query by primary key
     *
     * @param primaryKeyValue primary key value
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue);
}