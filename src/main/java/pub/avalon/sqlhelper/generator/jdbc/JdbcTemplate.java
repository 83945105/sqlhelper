package pub.avalon.sqlhelper.generator.jdbc;

import pub.avalon.sqlhelper.generator.beans.Column;

import java.sql.SQLException;
import java.util.List;

/**
 * @author baichao
 * @date 2019/6/9
 */
public interface JdbcTemplate {

    /**
     * 判断表是否存在
     *
     * @param tableName 表名
     * @return
     * @throws SQLException
     */
    boolean isTableExist(String tableName) throws SQLException;

    /**
     * 查询主键列
     *
     * @param tableName 表名
     * @return
     * @throws SQLException
     */
    Column selectPrimaryKeyColumn(String tableName) throws SQLException;

    /**
     * 查询所有列
     *
     * @param tableName 表名
     * @return
     * @throws SQLException
     */
    List<Column> selectColumns(String tableName) throws SQLException;

}
