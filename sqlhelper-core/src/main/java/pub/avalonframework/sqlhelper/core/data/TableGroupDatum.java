package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableGroupDatum {

    private String tableAlias;

    private List<GroupDatum> groupData;

    public TableGroupDatum(String tableAlias, List<GroupDatum> groupData) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.groupData = groupData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<GroupDatum> getGroupData() {
        return groupData;
    }
}