package com.dt.core.jdbc;

import java.sql.*;

/**
 * jdbc
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractJdbcSource {

    protected Connection connection;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet;

    protected String driverClassName;

    protected String url;

    protected String username;

    protected String password;

    public AbstractJdbcSource(String driverClassName, String url, String username, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public boolean getConnection() {
        try {
            Class.forName(this.driverClassName);
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean closeConnection() {
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

    public void executeQuery(String sql, Object... args) {
        try {
            this.preparedStatement = this.connection.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    this.preparedStatement.setObject(i + 1, args[i]);
                }
            }
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql, Object... args) {
        int count;
        try {
            this.preparedStatement = this.connection.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    this.preparedStatement.setObject(i + 1, args[i]);
                }
            }
            count = this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return count;
    }
}
