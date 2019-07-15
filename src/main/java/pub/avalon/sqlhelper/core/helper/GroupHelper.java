package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.GroupSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.GroupDatum;

/**
 * 分组助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class GroupHelper<T extends GroupHelper<T>> extends Helper<T, GroupDatum> {

    public GroupHelper(String tableAlias) {
        super(tableAlias, new GroupSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected GroupSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (GroupSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
