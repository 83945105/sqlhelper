package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.GroupDatum;

/**
 * On Sql模组
 *
 * @author 白超
 * @date 2019/5/18
 */
public class GroupSqlModel<T extends GroupSqlModel<T>> extends SqlModel<T, GroupDatum> {

    public GroupSqlModel(GroupSqlDataBuilder<T> groupSqlDataBuilder) {
        super(groupSqlDataBuilder);
    }

    @Override
    protected GroupSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (GroupSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
