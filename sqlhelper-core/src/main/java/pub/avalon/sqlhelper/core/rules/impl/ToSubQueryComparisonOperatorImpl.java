package pub.avalon.sqlhelper.core.rules.impl;

import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.ComparisonType;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.rules.ToSubQueryComparisonOperator;

/**
 * @author baichao
 */
public interface ToSubQueryComparisonOperatorImpl<T, S extends AbstractComparisonSqlPartDatum<S>> extends ToSubQueryComparisonOperator<T> {

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
    default T equalToSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T notEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.NOT_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T greaterThanSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.GREATER, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.GREATER_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lessThanSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.LESS, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.LESS_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T likeSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.LIKE, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T inSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T notInSubQuery(SubQueryCallback subQueryCallback) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.NOT_IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }
}