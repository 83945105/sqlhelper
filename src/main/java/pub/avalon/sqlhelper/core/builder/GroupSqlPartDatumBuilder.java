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

    private String tableName;
    private String tableAlias;
    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName) {
        this.addSqlPartDatum(new GroupDatum(this.tableName == null ? tableName : this.tableName, this.tableAlias == null ? tableAlias : this.tableAlias, columnName, columnAlias, fieldName));
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

}
