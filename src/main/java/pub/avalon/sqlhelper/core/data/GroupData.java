package pub.avalon.sqlhelper.core.data;

import java.util.List;

/**
 * 分组数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class GroupData {

    private AbstractTableData tableData;

    private List<String> columnNames;

    public GroupData(AbstractTableData tableData, List<String> columnNames) {
        this.tableData = tableData;
        this.columnNames = columnNames;
    }

    public AbstractTableData getTableData() {
        return tableData;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }
}
