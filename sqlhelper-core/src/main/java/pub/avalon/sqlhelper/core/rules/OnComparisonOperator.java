package pub.avalon.sqlhelper.core.rules;

import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.rules.impl.*;

/**
 * @author baichao
 */
public interface OnComparisonOperator<T extends Helper> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, OnDatum>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, OnDatum>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, OnDatum>,
        ToSqlPartBuilderComparisonOperator<T, OnSqlPartDatumBuilder>,
        ToSqlPartBuilderComparisonOperatorImpl<T, OnDatum, OnSqlPartDatumBuilder> {

}