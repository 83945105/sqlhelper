package pub.avalon.sqlhelper.core.data;

import java.util.Set;

/**
 * 表分组数据
 *
 * @author 白超
 * @date 2019/5/6
 */
public final class TableGroupDatum {

    private String tableAlias;

    private Set<GroupDatum> groupData;

    public TableGroupDatum(String tableAlias, Set<GroupDatum> groupData) {
        if (tableAlias == null) {
            throw new RuntimeException("tableAlias can not be null.");
        }
        this.tableAlias = tableAlias;
        this.groupData = groupData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Set<GroupDatum> getGroupData() {
        return groupData;
    }
    
}
