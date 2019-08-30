package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.HavingDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public class HavingSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, HavingDatum> {

    private HavingDatum havingDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public HavingSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {

    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {

    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }
}