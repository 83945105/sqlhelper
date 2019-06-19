package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 分组Sql片段数据构建器
 *
 * @author 白超
 * @date 2019/5/6
 */
public final class GroupSqlPartDatumBuilder<T extends Helper<T, GroupDatum>> extends AbstractSqlPartDatumBuilder<T, GroupDatum> {

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName) {
        this.addSqlPartDatum(new GroupDatum(tableName, tableAlias, columnName, columnAlias, fieldName));
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

}
