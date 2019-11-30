package pub.avalonframework.sqlhelper.generator.jdbc;

import java.sql.*;

/**
 * @author baichao
 */
public abstract class AbstractJdbcTemplate implements JdbcTemplate {

    protected Connection connection;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet;

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

    public AbstractJdbcTemplate(String driverClassName, String jdbcUrl, String username, String password) {
        this.driverClassName = driverClassName;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    protected boolean connection() {
        try {
            Class.forName(this.driverClassName);
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected boolean closeConnection() {
        if (this.resultSet != null) {
            try {
                this.resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (this.preparedStatement != null) {
            try {
                this.preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    protected void executeQuery(String sql, Object... args) throws SQLException {
        this.preparedStatement = this.connection.prepareStatement(sql);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                this.preparedStatement.setObject(i + 1, args[i]);
            }
        }
        this.resultSet = this.preparedStatement.executeQuery();
    }
}