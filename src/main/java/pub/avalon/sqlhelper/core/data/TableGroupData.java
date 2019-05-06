package pub.avalon.sqlhelper.core.data;

import java.util.Objects;
import java.util.Set;

/**
 * 表分组数据
 *
 * @author 白超
 * @date 2019/5/6
 */
public class TableGroupData {

    private TableData tableData;

    private Set<GroupDatum> groupData;

    public TableGroupData(TableData tableData, Set<GroupDatum> groupData) {
        this.tableData = tableData;
        this.groupData = groupData;
    }

    public TableData getTableData() {
        return tableData;
    }

    public Set<GroupDatum> getGroupData() {
        return groupData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableGroupData that = (TableGroupData) o;
        return Objects.equals(getTableData(), that.getTableData()) &&
                Objects.equals(getGroupData(), that.getGroupData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableData(), getGroupData());
    }

}
