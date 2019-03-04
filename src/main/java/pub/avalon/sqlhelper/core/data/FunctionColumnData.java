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

    private AbstractTableData tableData;

    private FunctionColumnType functionColumnType;

    private String column;

    private String alias;

    public FunctionColumnData(AbstractTableData tableData, FunctionColumnType functionColumnType, String column, String alias) {
        this.tableData = tableData;
        this.functionColumnType = functionColumnType;
        this.column = column;
        this.alias = alias;
    }

    public AbstractTableData getTableData() {
        return tableData;
    }

    public FunctionColumnType getFunctionColumnType() {
        return functionColumnType;
    }

    public String getColumn() {
        return column;
    }

    public String getAlias() {
        return alias;
    }

}
