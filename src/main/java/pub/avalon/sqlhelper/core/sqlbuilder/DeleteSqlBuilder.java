package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface DeleteSqlBuilder {

    /**
     * 删除
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult delete();

    /**
     * 根据主键删除
     *
     * @param primaryKeyValue 主键值
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param primaryKeyValues 主键值集合
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues);

}
