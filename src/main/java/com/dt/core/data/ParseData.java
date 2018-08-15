package com.dt.core.data;

import java.util.List;

/**
 * 解析数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ParseData {

    private String sql;

    private List<Object> args;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
