package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author 白超
 * @date 2019/7/23
 */
public interface TableSqlBuilder {

    /**
     * 复制表
     *
     * @param targetTableName 目标表名
     * @param copyData        是否复制表数据
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult copyTable(String targetTableName, boolean copyData);

    /**
     * 删除表
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult deleteTable();

    /**
     * 重命名表
     *
     * @param newTableName 新的表名
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult renameTable(String newTableName);

    /**
     * 判断表是否存在
     *
     * @return {@link DefaultSqlBuilder}
     */
    SqlBuilderResult isTableExist();

}
