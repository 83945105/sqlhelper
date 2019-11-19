package pub.avalon.sqlhelper.core.rules.impl;

import pub.avalon.sqlhelper.core.builder.AbstractComparisonSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.ComparisonType;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.rules.ToSqlPartBuilderComparisonOperator;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public interface ToSqlPartBuilderComparisonOperatorImpl<T extends Helper, S extends AbstractComparisonSqlPartDatum<S>, SB extends AbstractComparisonSqlPartDatumBuilder> extends ToSqlPartBuilderComparisonOperator<T, SB> {

    /**
     * get helper
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * get abstract comparison sql part datum
     *
     * @return extends {@link AbstractComparisonSqlPartDatum}
     */
    AbstractComparisonSqlPartDatum<S> getAbstractComparisonSqlPartDatum();

    /**
     * add sql part datum
     *
     * @param sqlPartDatum implements {@link SqlPartDatum} object
     */
    void addSqlPartDatum(S sqlPartDatum);

    @Override
    default T equalTo(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T notEqualTo(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.NOT_EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T greaterThan(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER_EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T lessThan(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS_EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T between(SB sqlPartDatumBuilder, SB secondOnSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetPairSqlPartDatum(ComparisonType.BETWEEN, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum(), secondOnSqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T like(SB sqlPartDatumBuilder) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LIKE, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T in(SB... sqlPartDatumBuilders) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.IN, Arrays.stream(sqlPartDatumBuilders).map(AbstractComparisonSqlPartDatumBuilder::getAbstractComparisonSqlPartDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    default T notIn(SB... sqlPartDatumBuilders) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.NOT_IN, Arrays.stream(sqlPartDatumBuilders).map(AbstractComparisonSqlPartDatumBuilder::getAbstractComparisonSqlPartDatum).collect(Collectors.toList())));
        return this.getHelper();
    }
}