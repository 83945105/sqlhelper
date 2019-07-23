package pub.avalon.sqlhelper.core.sqlbuilder;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface TableSqlBuilder extends SqlBuilder {

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilder isTableExist();

}
