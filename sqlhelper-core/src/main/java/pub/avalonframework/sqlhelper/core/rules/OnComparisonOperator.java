package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.OnDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.impl.*;

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