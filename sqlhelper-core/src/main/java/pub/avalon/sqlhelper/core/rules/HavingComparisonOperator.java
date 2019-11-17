package pub.avalon.sqlhelper.core.rules;

import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.HavingDatum;
import pub.avalon.sqlhelper.core.rules.impl.BaseComparisonOperatorImpl;
import pub.avalon.sqlhelper.core.rules.impl.ToColumnCallbackComparisonOperatorImpl;
import pub.avalon.sqlhelper.core.rules.impl.ToColumnComparisonOperatorImpl;
import pub.avalon.sqlhelper.core.rules.impl.ToSubQueryComparisonOperatorImpl;

/**
 * @author baichao
 */
public interface HavingComparisonOperator<T> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, HavingDatum>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, HavingDatum>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, HavingDatum>,
        ToSqlPartBuilderComparisonOperator<T, HavingSqlPartDatumBuilder> {

}