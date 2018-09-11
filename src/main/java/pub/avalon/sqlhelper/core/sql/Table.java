package pub.avalon.sqlhelper.core.sql;

/**
 * 表
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Table<T> extends Sql {

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return
     */
    T copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return
     */
    T deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return
     */
    T renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return
     */
    T isTableExist();

}
