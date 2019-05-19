package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.GroupDatum;

/**
 * 分组数据构建器
 *
 * @author 白超
 * @date 2019/5/6
 */
public final class GroupSqlDataBuilder<T extends SqlModel<T, GroupDatum>> extends AbstractSqlDataBuilder<T, GroupDatum> {

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.addSqlModelDatum(new GroupDatum(tableName, tableAlias, columnName, columnAlias));
    }

}
