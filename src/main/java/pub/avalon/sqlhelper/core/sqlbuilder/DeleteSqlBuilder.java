package pub.avalon.sqlhelper.core.sqlbuilder;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface DeleteSqlBuilder extends SqlBuilder {

    /**
     * 删除
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder delete();

    /**
     * 根据主键删除
     *
     * @param primaryKeyValue 主键值
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * 根据主键批量删除
     *
     * @param primaryKeyValues 主键值集合
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder batchDeleteByPrimaryKeys(Object... primaryKeyValues);

}
