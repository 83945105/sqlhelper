package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.FunctionColumnType;

/**
 * 函数列数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/13
 */
public final class FunctionColumnData {

    private TableData tableData;

    private FunctionColumnType functionColumnType;

    private String columnName;

    private String columnAlias;

    public FunctionColumnData(TableData tableData, FunctionColumnType functionColumnType, String columnName, String columnAlias) {
        this.tableData = tableData;
        this.functionColumnType = functionColumnType;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
    }

    public TableData getTableData() {
        return tableData;
    }

    public FunctionColumnType getFunctionColumnType() {
        return functionColumnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

}
