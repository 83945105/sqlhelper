package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.DeleteSqlBuilderResult;

/**
 * @author baichao
 */
public interface DeleteSqlBuilder {

    /**
     * delete
     *
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult delete();

    /**
     * delete by primary key
     *
     * @param primaryKeyValue primary key value
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * batch delete by primary keys
     *
     * @param primaryKeyValues primary key values
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues);
}