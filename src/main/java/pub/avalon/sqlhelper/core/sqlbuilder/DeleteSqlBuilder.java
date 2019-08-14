package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.DeleteSqlBuilderResult;

/**
 * @author baichao
 * @date 2019/7/23
 */
public interface DeleteSqlBuilder {

    /**
     * 删除
     *
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult delete();

    /**
     * 根据主键删除
     *
     * @param primaryKeyValue 主键值
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param primaryKeyValues 主键值集合
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues);

}
