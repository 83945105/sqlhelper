package com.dt.core.data;

/**
 * 分组数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class GroupData {

    private AbstractTableData tableData;

    private String[] columnNames;

    public GroupData(AbstractTableData tableData, String[] columnNames) {
        this.tableData = tableData;
        this.columnNames = columnNames;
    }

    public AbstractTableData getTableData() {
        return tableData;
    }

    public String[] getColumnNames() {
        return columnNames;
    }
}
