package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;
import java.util.Set;

/**
 * 表列数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class TableGroupColumnDatum {

    private String tableAlias;

    private GroupType groupType;

    private List<ColumnDatum> columnData;

    public TableGroupColumnDatum(String tableAlias, GroupType groupType, List<ColumnDatum> columnData) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.groupType = groupType;
        this.columnData = columnData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public List<ColumnDatum> getColumnData() {
        return columnData;
    }
}
