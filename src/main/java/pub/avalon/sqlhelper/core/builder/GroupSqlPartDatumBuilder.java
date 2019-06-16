package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.helper.Helper;

/**
 * 分组Sql片段数据构建器
 *
 * @author 白超
 * @date 2019/5/6
 */
public final class GroupSqlPartDatumBuilder<T extends Helper<T, GroupDatum>> extends AbstractSqlPartDatumBuilder<T, GroupDatum> {

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.addSqlPartDatum(new GroupDatum(tableName, tableAlias, columnName, columnAlias));
    }

}
