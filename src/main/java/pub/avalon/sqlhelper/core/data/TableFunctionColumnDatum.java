package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.GroupType;

import java.util.Set;

/**
 * 表列数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class TableFunctionColumnDatum {

    private String tableAlias;

    private GroupType groupType;

    private Set<ColumnDatum> columnData;

    public TableFunctionColumnDatum(String tableAlias, GroupType groupType, Set<ColumnDatum> columnData) {
        if (tableAlias == null) {
            throw new RuntimeException("tableAlias can not be null.");
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

    public Set<ColumnDatum> getColumnData() {
        return columnData;
    }



}
