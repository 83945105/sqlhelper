package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.build.SqlBuilder;

/**
 * 表
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Table extends Sql {

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return
     */
    SqlBuilder copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return
     */
    SqlBuilder deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return
     */
    SqlBuilder renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return
     */
    SqlBuilder isTableExist();

}
