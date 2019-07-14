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

    public ColumnSqlPartDatumBuilder(String tableAlias) {
        super(tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.addSqlPartDatum(new ColumnDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

}
