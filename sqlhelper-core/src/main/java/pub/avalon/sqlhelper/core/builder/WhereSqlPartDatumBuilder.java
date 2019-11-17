package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.ComparisonType;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.rules.WhereComparisonOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public final class WhereSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, WhereDatum> implements WhereComparisonOperator<T> {

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
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    @Override
    public T equalTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.EQUAL, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T notEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.NOT_EQUAL, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.GREATER, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.GREATER_EQUAL, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.LESS, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.LESS_EQUAL, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T between(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder, WhereSqlPartDatumBuilder secondWhereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.BETWEEN, new ArrayList<WhereDatum>(2) {{
            add(whereSqlPartDatumBuilder.whereDatum);
            add(secondWhereSqlPartDatumBuilder.whereDatum);
        }}));
        return this.getHelper();
    }

    @Override
    public T like(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.LIKE, Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T in(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.IN, Arrays.stream(whereSqlPartDatumBuilders).map(whereSqlPartDatumBuilder -> whereSqlPartDatumBuilder.whereDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    public T notIn(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders) {
        this.addSqlPartDatum(this.whereDatum.setTargetWhereData(ComparisonType.NOT_IN, Arrays.stream(whereSqlPartDatumBuilders).map(whereSqlPartDatumBuilder -> whereSqlPartDatumBuilder.whereDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    public AbstractComparisonSqlPartDatum<WhereDatum> getAbstractComparisonSqlPartDatum() {
        return this.whereDatum;
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
    }
}