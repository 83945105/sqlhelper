package pub.avalon.sqlhelper.core.jdbc;

import pub.avalon.beans.ColumnFieldConverter;
import pub.avalon.sqlhelper.core.model.ColumnInfo;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * jdbc引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class JdbcSourceEngine extends AbstractJdbcSource {

    private String dataBaseName;

    private static final Pattern MYSQL_DATABASE_NAME_PATTERN = Pattern.compile("^jdbc:mysql://(.*?)/(.*?)(\\?useSSL=false)?$");

    public static JdbcSourceEngine newMySqlEngine(String driverClassName, String url, String username, String password) {
        JdbcSourceEngine engine = new JdbcSourceEngine(driverClassName, url, username, password);
        Matcher matcher = MYSQL_DATABASE_NAME_PATTERN.matcher(url);
        while (matcher.find()) {
            engine.dataBaseName = matcher.group(2);
        }
        return engine;
    }

    private JdbcSourceEngine(String driverClassName, String url, String username, String password) {
        super(driverClassName, url, username, password);
    }

    private void link() {
        if (this.connection == null) {
            if (this.getConnection()) {
                return;
            }
            throw new RuntimeException("can not connect to " + this.url);
        }
    }

    public List<String> getTableNames() throws SQLException {
        this.link();
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + this.dataBaseName + "'";
        this.executeQuery(sql);
        List<String> tableNames = new ArrayList<>();
        while (this.resultSet.next()) {
            tableNames.add(this.resultSet.getString(1));
        }
        return tableNames;
    }

    public ColumnInfo getPrimaryKeyColumn(String tableName, ColumnFieldConverter columnFieldConverter) throws SQLException {
        link();
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName + "' AND COLUMN_KEY='PRI' LIMIT 1";
        this.executeQuery(sql);
        ColumnInfo columnInfo = null;
        while (this.resultSet.next()) {
            columnInfo = new ColumnInfo();
            columnInfo.setName(this.resultSet.getString(1));
            columnInfo.setAlias(columnFieldConverter.columnNameToFieldName(columnInfo.getName()));
            break;
        }
        sql = "SHOW FULL COLUMNS FROM " + tableName;
        this.executeQuery(sql);
        while (this.resultSet.next()) {
            if (!this.resultSet.getString("Field").equals(columnInfo.getName())) {
                continue;
            }
            columnInfo.setTypeString(this.resultSet.getString("Type"));
            columnInfo.setCollation(this.resultSet.getString("Collation"));
            columnInfo.setNullString(this.resultSet.getString("Null"));
            columnInfo.setKeyString(this.resultSet.getString("Key"));
            columnInfo.setDefaultString(this.resultSet.getString("Default"));
            columnInfo.setExtra(this.resultSet.getString("Extra"));
            columnInfo.setComment(this.resultSet.getString("Comment"));
        }
        return columnInfo;
    }

    public LinkedHashMap<String, String> getColumnAndField(String tableName, ColumnFieldConverter columnFieldConverter) throws SQLException {
        link();
        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        this.executeQuery(sql);
        LinkedHashMap<String, String> columnAndField = new LinkedHashMap<>();
        ResultSetMetaData resultSetMetaData = this.resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            String columnName = resultSetMetaData.getColumnName(i);
            columnAndField.put(columnName, columnFieldConverter.columnNameToFieldName(columnName));
        }
        return columnAndField;
    }

    public LinkedHashMap<String, String> getFieldAndColumn(String tableName, ColumnFieldConverter columnFieldConverter) throws SQLException {
        link();
        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        this.executeQuery(sql);
        LinkedHashMap<String, String> fieldAndColumn = new LinkedHashMap<>();
        ResultSetMetaData resultSetMetaData = this.resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            String columnName = resultSetMetaData.getColumnName(i);
            fieldAndColumn.put(columnFieldConverter.columnNameToFieldName(columnName), columnName);
        }
        return fieldAndColumn;
    }

    public Collection<ColumnInfo> getColumns(String tableName, ColumnFieldConverter columnFieldConverter) throws SQLException {
        link();
        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        this.executeQuery(sql);
        Map<String, ColumnInfo> columns = new LinkedHashMap<>();
        ResultSetMetaData resultSetMetaData = this.resultSet.getMetaData();
        ColumnInfo columnInfo;
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            columnInfo = new ColumnInfo();
            String columnName = resultSetMetaData.getColumnName(i);
            columnInfo.setName(columnName);
            columnInfo.setAlias(columnFieldConverter.columnNameToFieldName(columnName));
            columnInfo.setCatalog(resultSetMetaData.getCatalogName(i));
            columnInfo.setLabel(resultSetMetaData.getColumnLabel(i));
            columnInfo.setCurrency(resultSetMetaData.isCurrency(i));
            columnInfo.setReadOnly(resultSetMetaData.isReadOnly(i));
            columnInfo.setAutoIncrement(resultSetMetaData.isAutoIncrement(i));
            columnInfo.setTypeInt(resultSetMetaData.getColumnType(i));
            columns.put(columnName, columnInfo);
        }
        sql = "SHOW FULL COLUMNS FROM " + tableName;
        this.executeQuery(sql);
        while (this.resultSet.next()) {
            columnInfo = columns.get(this.resultSet.getString("Field"));
            if (columnInfo == null) {
                continue;
            }
            columnInfo.setTypeString(this.resultSet.getString("Type"));
            columnInfo.setCollation(this.resultSet.getString("Collation"));
            columnInfo.setNullString(this.resultSet.getString("Null"));
            columnInfo.setKeyString(this.resultSet.getString("Key"));
            columnInfo.setDefaultString(this.resultSet.getString("Default"));
            columnInfo.setExtra(this.resultSet.getString("Extra"));
            columnInfo.setComment(this.resultSet.getString("Comment"));
        }
        return columns.values();
    }

    public boolean isTableExist(String tableName) throws SQLException {
        link();
        String sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE table_name = '" + tableName + "'";
        this.executeQuery(sql);
        return this.resultSet.next() && this.resultSet.getInt(1) > 0;
    }

    public boolean isColumnExist(String tableName, String columnName) throws SQLException {
        link();
        String sql = "SELECT COUNT(*) FROM information_schema.COLUMNS WHERE table_name = '" + tableName + "' AND column_name = '" + columnName + "'";
        this.executeQuery(sql);
        return this.resultSet.next() && this.resultSet.getInt(1) > 0;
    }
}
