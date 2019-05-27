package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.FunctionColumnType;

import java.util.Objects;
import java.util.Set;

/**
 * 表列数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class TableFunctionColumnDatum {

    private TableData tableData;

    private FunctionColumnType functionColumnType;

    private Set<ColumnDatum> columnData;

    public TableFunctionColumnDatum(TableData tableData, FunctionColumnType functionColumnType, Set<ColumnDatum> columnData) {
        this.tableData = tableData;
        this.functionColumnType = functionColumnType;
        this.columnData = columnData;
    }

    public TableData getTableData() {
        return tableData;
    }

    public FunctionColumnType getFunctionColumnType() {
        return functionColumnType;
    }

    public Set<ColumnDatum> getColumnData() {
        return columnData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableFunctionColumnDatum that = (TableFunctionColumnDatum) o;
        return Objects.equals(getTableData(), that.getTableData()) &&
                getFunctionColumnType() == that.getFunctionColumnType() &&
                Objects.equals(getColumnData(), that.getColumnData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableData(), getFunctionColumnType(), getColumnData());
    }
    
}
