package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.rules.OnComparisonOperator;

/**
 * @author baichao
 */
public final class OnSqlPartDatumBuilder<T extends Helper> extends AbstractComparisonSqlPartDatumBuilder<T, OnDatum> implements OnComparisonOperator<T> {

    private OnDatum onDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public OnSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.onDatum = new OnDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.onDatum = new OnDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
    }

    @Override
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<OnDatum> abstractComparisonSqlPartDatum) {
        super.addSqlPartDatum((OnDatum) abstractComparisonSqlPartDatum);
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    @Override
    public AbstractComparisonSqlPartDatum<OnDatum> getAbstractComparisonSqlPartDatum() {
        return this.onDatum;
    }

    @Override
    public AbstractComparisonSqlPartDatum<OnDatum> getCloneComparisonSqlPartDatum() {
        return this.onDatum.getCloneComparisonSqlPartDatum();
    }
}