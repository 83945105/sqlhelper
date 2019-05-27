package pub.avalon.sqlhelper.core.data;

import java.util.Objects;
import java.util.Set;

/**
 * 表列数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class TableColumnDatum {

    private TableData tableData;

    private Set<ColumnDatum> columnData;

    public TableColumnDatum(TableData tableData, Set<ColumnDatum> columnData) {
        tableData.setColumnData(columnData);
        this.tableData = tableData;
        this.columnData = columnData;
    }

    public TableData getTableData() {
        return tableData;
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
        TableColumnDatum that = (TableColumnDatum) o;
        return Objects.equals(getTableData(), that.getTableData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableData());
    }
}
