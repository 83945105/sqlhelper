package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.ComparisonType;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.rules.OnComparisonOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public final class OnSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, OnDatum> implements OnComparisonOperator<T> {

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
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    @Override
    public T equalTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.EQUAL, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T notEqualTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.NOT_EQUAL, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThan(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.GREATER, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.GREATER_EQUAL, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThan(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.LESS, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.LESS_EQUAL, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T between(OnSqlPartDatumBuilder onSqlPartDatumBuilder, OnSqlPartDatumBuilder secondOnSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.BETWEEN, new ArrayList<OnDatum>(2) {{
            add(onSqlPartDatumBuilder.onDatum);
            add(secondOnSqlPartDatumBuilder.onDatum);
        }}));
        return this.getHelper();
    }

    @Override
    public T like(OnSqlPartDatumBuilder onSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.LIKE, Collections.singletonList(onSqlPartDatumBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T in(OnSqlPartDatumBuilder... onSqlPartDatumBuilders) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.IN, Arrays.stream(onSqlPartDatumBuilders).map(onSqlPartDatumBuilder -> onSqlPartDatumBuilder.onDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    public T notIn(OnSqlPartDatumBuilder... onSqlPartDatumBuilders) {
        this.addSqlPartDatum(this.onDatum.setTargetOnData(ComparisonType.NOT_IN, Arrays.stream(onSqlPartDatumBuilders).map(onSqlPartDatumBuilder -> onSqlPartDatumBuilder.onDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    public AbstractComparisonSqlPartDatum<OnDatum> getAbstractComparisonSqlPartDatum() {
        return this.onDatum;
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
    }
}