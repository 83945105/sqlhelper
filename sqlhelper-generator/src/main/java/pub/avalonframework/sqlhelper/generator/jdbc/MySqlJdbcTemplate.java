package pub.avalonframework.sqlhelper.generator.jdbc;

import pub.avalonframework.sqlhelper.generator.beans.Column;
import pub.avalonframework.sqlhelper.generator.beans.MySqlColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author baichao
 */
public class MySqlJdbcTemplate extends AbstractJdbcTemplate {

    private String dataBaseName;

    private static final Pattern MYSQL_DATABASE_NAME_PATTERN = Pattern.compile("^jdbc:mysql://(.*?)/(.*?)(\\?(.*?))?$");

    public MySqlJdbcTemplate(String driverClassName, String jdbcUrl, String username, String password) {
        super(driverClassName, jdbcUrl, username, password);
        Matcher matcher = MYSQL_DATABASE_NAME_PATTERN.matcher(jdbcUrl);
        if (matcher.find()) {
            this.dataBaseName = matcher.group(2);
        }
        if (this.dataBaseName == null || "".equals(this.dataBaseName.trim())) {
            throw new RuntimeException("get dataBaseName fail.");
        }
    }

    @Override
    public boolean isTableExist(String tableName) {
        boolean connection = this.connection();
        if (!connection) {
            throw new RuntimeException("connection to [" + this.dataBaseName + "] fail.");
        }
        try {
            String sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE table_name = '" + tableName + "'";
            this.executeQuery(sql);
            return this.resultSet.next() && this.resultSet.getInt(1) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return false;
    }

    @Override
    public Column selectPrimaryKeyColumn(String tableName) {
        boolean connection = this.connection();
        if (!connection) {
            throw new RuntimeException("connection to [" + this.dataBaseName + "] fail.");
        }
        try {
            String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + this.dataBaseName + "' AND TABLE_NAME='" + tableName + "' AND COLUMN_KEY='PRI' LIMIT 1";
            this.executeQuery(sql);
            if (this.resultSet.next()) {
                String primaryKeyName = this.resultSet.getString(1);
                sql = "SHOW FULL COLUMNS FROM `" + tableName + "` where `field` = '" + primaryKeyName + "'";
                this.executeQuery(sql);
                if (this.resultSet.next()) {
                    MySqlColumn column = new MySqlColumn();
                    column.setName(this.resultSet.getString("Field"));
                    column.setType(typeToJavaType(this.resultSet.getString("Type")));
                    column.setComment(this.resultSet.getString("Comment"));
                    return column;
                }
                return null;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return null;
    }

    @Override
    public List<Column> selectColumns(String tableName) {
        boolean connection = this.connection();
        if (!connection) {
            throw new RuntimeException("connection to [" + this.dataBaseName + "] fail.");
        }
        try {
            String sql = "SHOW FULL COLUMNS FROM " + tableName;
            this.executeQuery(sql);
            List<Column> columns = new ArrayList<>();
            while (this.resultSet.next()) {
                MySqlColumn column = new MySqlColumn();
                column.setName(this.resultSet.getString("Field"));
                column.setType(typeToJavaType(this.resultSet.getString("Type")));
                column.setComment(this.resultSet.getString("Comment"));
                columns.add(column);
            }
            return columns;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return null;
    }

    private String typeToJavaType(String type) {
        return type.replaceAll("\\(.*?\\)|\\{.*?}|\\[.*?]|（.*?）", "").trim().toUpperCase();
    }
}