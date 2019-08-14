package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * 表分组数据
 *
 * @author baichao
 * @date 2019/5/6
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
