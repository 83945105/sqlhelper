package pub.avalon.sqlhelper.generator.jdbc;

import pub.avalon.sqlhelper.generator.beans.Column;

import java.sql.SQLException;
import java.util.List;

/**
 * @author baichao
 */
public interface JdbcTemplate {

    boolean isTableExist(String tableName);

    Column selectPrimaryKeyColumn(String tableName);

    List<Column> selectColumns(String tableName);
}