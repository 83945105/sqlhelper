package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.WhereDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.rules.WhereComparisonOperator;

/**
 * @author baichao
 */
public final class WhereSqlPartDatumBuilder<T extends Helper> extends AbstractComparisonSqlPartDatumBuilder<T, WhereDatum> implements WhereComparisonOperator<T> {

    private WhereDatum whereDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public WhereSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereDatum = new WhereDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.whereDatum = new WhereDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
    }

    @Override
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<WhereDatum> abstractComparisonSqlPartDatum) {
        super.addSqlPartDatum((WhereDatum) abstractComparisonSqlPartDatum);
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    @Override
    public AbstractComparisonSqlPartDatum<WhereDatum> getAbstractComparisonSqlPartDatum() {
        return this.whereDatum;
    }

    @Override
    public AbstractComparisonSqlPartDatum<WhereDatum> getCloneComparisonSqlPartDatum() {
        return this.whereDatum.getCloneComparisonSqlPartDatum();
    }
}