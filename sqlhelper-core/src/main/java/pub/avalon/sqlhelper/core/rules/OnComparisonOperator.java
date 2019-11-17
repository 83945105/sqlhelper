package pub.avalon.sqlhelper.core.rules;

import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.rules.impl.BaseComparisonOperatorImpl;
import pub.avalon.sqlhelper.core.rules.impl.ToColumnCallbackComparisonOperatorImpl;
import pub.avalon.sqlhelper.core.rules.impl.ToColumnComparisonOperatorImpl;
import pub.avalon.sqlhelper.core.rules.impl.ToSubQueryComparisonOperatorImpl;

/**
 * @author baichao
 */
public interface OnComparisonOperator<T> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, OnDatum>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, OnDatum>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, OnDatum>,
        ToSqlPartBuilderComparisonOperator<T, OnSqlPartDatumBuilder> {

}