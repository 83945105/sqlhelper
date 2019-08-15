package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class ColumnSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, ColumnDatum> {

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public ColumnSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.addSqlPartDatum(new ColumnDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.addSqlPartDatum(new ColumnDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }
}