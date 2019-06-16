package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.Set;

/**
 * 表列数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class TableFunctionColumnDatum<T extends TableHelper> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private GroupType groupType;

    private Set<ColumnDatum> columnData;

    public TableFunctionColumnDatum(Class<T> tableHelperClass, String tableAlias, GroupType groupType, Set<ColumnDatum> columnData) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.groupType = groupType;
        this.columnData = columnData;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
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
