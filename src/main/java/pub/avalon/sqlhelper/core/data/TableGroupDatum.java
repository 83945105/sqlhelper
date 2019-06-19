package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.Set;

/**
 * 表分组数据
 *
 * @author 白超
 * @date 2019/5/6
 */
public final class TableGroupDatum<T extends TableHelper> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private Set<GroupDatum> groupData;

    public TableGroupDatum(Class<T> tableHelperClass, String tableAlias, Set<GroupDatum> groupData) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.groupData = groupData;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Set<GroupDatum> getGroupData() {
        return groupData;
    }
    
}
