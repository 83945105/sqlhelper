package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.GroupSqlDataBuilder;
import pub.avalon.sqlhelper.core.data.GroupDatum;

/**
 * Group 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class GroupHelper<T extends GroupHelper<T>> extends Helper<T, GroupDatum> {

    public GroupHelper(GroupSqlDataBuilder<T> groupSqlDataBuilder) {
        super(groupSqlDataBuilder);
    }

    @Override
    protected GroupSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (GroupSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
