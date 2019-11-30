package pub.avalonframework.sqlhelper.core.rules.impl;

import pub.avalonframework.sqlhelper.core.builder.AbstractComparisonSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.ToSqlPartBuilderComparisonOperator;

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
     * Add abstract comparison sql part datum.
     *
     * @param abstractComparisonSqlPartDatum Implements {@link AbstractComparisonSqlPartDatum} object.
     */
    void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<S> abstractComparisonSqlPartDatum);

    @Override
    default T equalTo(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T notEqualTo(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.NOT_EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T greaterThan(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER_EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T lessThan(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS_EQUAL, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T between(SB sqlPartDatumBuilder, SB secondOnSqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetPairSqlPartDatum(ComparisonType.BETWEEN, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum(), secondOnSqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T like(SB sqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LIKE, sqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T in(SB... sqlPartDatumBuilders) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.IN, Arrays.stream(sqlPartDatumBuilders).map(AbstractComparisonSqlPartDatumBuilder::getAbstractComparisonSqlPartDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    default T notIn(SB... sqlPartDatumBuilders) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.NOT_IN, Arrays.stream(sqlPartDatumBuilders).map(AbstractComparisonSqlPartDatumBuilder::getAbstractComparisonSqlPartDatum).collect(Collectors.toList())));
        return this.getHelper();
    }
}