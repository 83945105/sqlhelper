package pub.avalon.sqlhelper.core.data;

import java.util.Map;
import java.util.Objects;

/**
 * @author 白超
 * @date 2019/3/4
 */
public final class ColumnData {

    private AbstractTableData tableData;

    private Map<String, String> columnAliasMap;

    public ColumnData(AbstractTableData tableData, Map<String, String> columnAliasMap) {
        tableData.setColumnData(this);
        this.tableData = tableData;
        this.columnAliasMap = columnAliasMap;
    }

    public AbstractTableData getTableData() {
        return tableData;
    }

    public Map<String, String> getColumnAliasMap() {
        return columnAliasMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColumnData that = (ColumnData) o;
        return Objects.equals(tableData, that.tableData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableData);
    }
}
