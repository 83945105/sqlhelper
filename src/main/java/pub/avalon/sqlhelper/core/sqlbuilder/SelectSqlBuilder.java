package pub.avalon.sqlhelper.core.sqlbuilder;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface SelectSqlBuilder extends SqlBuilder {

    /**
     * 查询
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder query();

    /**
     * 查询数量
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder queryCount();

    /**
     * 根据主键查询
     *
     * @param primaryKeyValue 主键值
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder queryByPrimaryKey(Object primaryKeyValue);

}
