package pub.avalon.sqlhelper.core.data;

import java.util.Objects;
import java.util.Set;

/**
 * 排序数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class TableSortData {

    private TableData tableData;

    private Set<SortDatum> sortData;

    public TableSortData(TableData tableData, Set<SortDatum> sortData) {
        this.tableData = tableData;
        this.sortData = sortData;
    }

    public TableData getTableData() {
        return tableData;
    }

    public Set<SortDatum> getSortData() {
        return sortData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableSortData that = (TableSortData) o;
        return Objects.equals(getTableData(), that.getTableData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableData());
    }
}
