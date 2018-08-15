package com.dt.beans;

import com.dt.core.bean.DataBaseType;

/**
 * 分页工具类
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class Pagination {

    /**
     * 数据库类型
     */
    private DataBaseType dataBaseType = DataBaseType.MYSQL;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 当前页号
     */
    private int currentPage = 1;
    /**
     * 每页显示条数
     */
    private int pageSize = 1;
    /**
     * Oracle起始记录号
     */
    private int oracleStartNum;
    /**
     * Oracle结束记录号
     */
    private int oracleEndNum;
    /**
     * MySQL起始记录号
     */
    private int mySqlStartNum = -1;

    public Pagination() {
    }

    public Pagination(int currentPage, int pageSize) {
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
    }

    public Pagination(int total, int currentPage, int pageSize) {
        this.setTotal(total);
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
    }

    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public int getTotal() {
        return this.total;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageCount() {
        if (this.total % this.pageSize == 0) {
            return this.total / this.pageSize;
        } else if (total % pageSize > 0) {
            return this.total / this.pageSize + 1;
        } else {
            return 1;
        }
    }

    /**
     * 设置总记录数
     */
    public void setTotal(int total) {
        this.total = total > 0 ? total : 0;
    }

    /**
     * 设置当前页号
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage > 0 ? currentPage : 1;
    }

    /**
     * 设置每页显示条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : 1;
    }

    public int getOracleStartNum() {
        return this.oracleStartNum != 0 ? this.oracleStartNum : (this.currentPage - 1) * this.pageSize + 1;
    }

    public void setOracleStartNum(int oracleStartNum) {
        this.oracleStartNum = oracleStartNum > 0 ? oracleStartNum : 1;
    }

    public int getOracleEndNum() {
        return this.oracleEndNum != 0 ? this.oracleEndNum : this.currentPage * this.pageSize;
    }

    public void setOracleEndNum(int oracleEndNum) {
        this.oracleEndNum = oracleEndNum > 0 ? oracleEndNum : 1;
    }

    public int getMySqlStartNum() {
        return this.mySqlStartNum != -1 ? this.mySqlStartNum : (this.currentPage - 1) * this.pageSize;
    }

    public void setMySqlStartNum(int mySqlStartNum) {
        this.mySqlStartNum = mySqlStartNum;
    }

    public void setMySQLStartNum(int mySQLStartNum) {
        this.mySqlStartNum = mySQLStartNum > 0 ? mySQLStartNum : 0;
    }

    public Integer getLimitStart() {
        switch (this.dataBaseType) {
            case ORACLE:
                return this.getOracleStartNum();
            case MYSQL:
                return this.getMySqlStartNum();
            default:
                return null;
        }
    }

    public void setLimitStart(int limitStart) {
        switch (this.dataBaseType) {
            case ORACLE:
                this.setOracleStartNum(limitStart);
                return;
            case MYSQL:
                this.setMySQLStartNum(limitStart);
                return;
            default:
                return;
        }
    }

    public int getLimitEnd() {
        switch (this.dataBaseType) {
            case ORACLE:
                return this.getOracleEndNum();
            case MYSQL:
                return this.getPageSize();
            default:
                return 0;
        }
    }

    public void setLimitEnd(int limitEnd) {
        switch (this.dataBaseType) {
            case ORACLE:
                this.setOracleEndNum(limitEnd);
                return;
            case MYSQL:
                this.setPageSize(limitEnd);
                return;
            default:
                return;
        }
    }

}
