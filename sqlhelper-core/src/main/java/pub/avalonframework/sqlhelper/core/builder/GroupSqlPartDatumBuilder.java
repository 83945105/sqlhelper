package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.GroupDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class GroupSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, GroupDatum> {

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public GroupSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.addSqlPartDatum(new GroupDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.addSqlPartDatum(new GroupDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }
}