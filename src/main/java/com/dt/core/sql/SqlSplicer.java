package com.dt.core.sql;

/**
 * sql拼接器
 *
 * @author 白超
 * @date 2018/8/22
 */
public class SqlSplicer {

    private StringBuilder sql;

    public SqlSplicer() {
        this.sql = new StringBuilder();
    }

    public SqlSplicer(int capacity) {
        this.sql = new StringBuilder(capacity);
    }

    public SqlSplicer clear() {
        this.sql.replace(0, this.sql.length(), "");
        return this;
    }

    public SqlSplicer append(String sqlPart) {
        this.sql.append(sqlPart);
        return this;
    }

    public String getSql() {
        return this.sql.toString();
    }

    public int length() {
        return this.sql.length();
    }
}
