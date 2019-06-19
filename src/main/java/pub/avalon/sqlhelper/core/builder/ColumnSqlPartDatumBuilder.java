package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 列Sql片段数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class ColumnSqlPartDatumBuilder<S extends Helper<S, ColumnDatum>> extends AbstractSqlPartDatumBuilder<S, ColumnDatum> {

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName) {
        this.addSqlPartDatum(new ColumnDatum(tableName, tableAlias, columnName, columnAlias, fieldName));
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

}
