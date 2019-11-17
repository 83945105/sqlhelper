package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.HavingDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.rules.GroupByOperator;
import pub.avalon.sqlhelper.core.rules.HavingComparisonOperator;

/**
 * @author baichao
 */
public class HavingSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, HavingDatum> implements HavingComparisonOperator<T>, GroupByOperator<HavingSqlPartDatumBuilder<T>> {

    private HavingDatum havingDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public HavingSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.havingDatum = new HavingDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.havingDatum = new HavingDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    @Override
    public ComparisonRule getDefaultComparisonRule() {
        return this.sqlBuilderOptions.getSqlPartDatumBuilderOptions().getDefaultHavingComparisonRule();
    }

    @Override
    public HavingSqlPartDatumBuilder<T> min() {
        return null;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> max() {
        return null;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> count() {
        return null;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> sum() {
        return null;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> avg() {
        return null;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> stddev() {
        return null;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> variance() {
        return null;
    }

    @Override
    public T equalTo(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T notEqualTo(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T greaterThan(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T greaterThanAndEqualTo(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T lessThan(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T lessThanAndEqualTo(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T between(HavingSqlPartDatumBuilder sqlPartDatumBuilder, HavingSqlPartDatumBuilder secondSqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T like(HavingSqlPartDatumBuilder sqlPartDatumBuilder) {
        return null;
    }

    @Override
    public T in(HavingSqlPartDatumBuilder... sqlPartDatumBuilders) {
        return null;
    }

    @Override
    public T notIn(HavingSqlPartDatumBuilder... sqlPartDatumBuilders) {
        return null;
    }

    @Override
    public AbstractComparisonSqlPartDatum<HavingDatum> getAbstractComparisonSqlPartDatum() {
        return this.havingDatum;
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
    }
}