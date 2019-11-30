package pub.avalonframework.sqlhelper.generator.jdbc;

import pub.avalonframework.sqlhelper.generator.beans.Column;

import java.util.List;

/**
 * @author baichao
 */
public interface JdbcTemplate {

    boolean isTableExist(String tableName);

    Column selectPrimaryKeyColumn(String tableName);

    List<Column> selectColumns(String tableName);
}