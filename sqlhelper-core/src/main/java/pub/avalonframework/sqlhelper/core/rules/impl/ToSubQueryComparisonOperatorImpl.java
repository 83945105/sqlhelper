package pub.avalonframework.sqlhelper.core.rules.impl;

import pub.avalonframework.sqlhelper.core.callback.SubQueryCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.ToSubQueryComparisonOperator;

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
     * Add abstract comparison sql part datum.
     *
     * @param abstractComparisonSqlPartDatum Implements {@link AbstractComparisonSqlPartDatum} object.
     */
    void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<S> abstractComparisonSqlPartDatum);

    @Override
    default T equalToSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T notEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.NOT_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T greaterThanSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.GREATER, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.GREATER_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lessThanSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.LESS, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.LESS_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T likeSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.LIKE, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T inSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T notInSubQuery(SubQueryCallback subQueryCallback) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSubQuery(ComparisonType.NOT_IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }
}