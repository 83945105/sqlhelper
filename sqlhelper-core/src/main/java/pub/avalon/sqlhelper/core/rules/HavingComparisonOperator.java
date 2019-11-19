package pub.avalon.sqlhelper.core.rules;

import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.HavingDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.rules.impl.*;

/**
 * @author baichao
 */
public interface HavingComparisonOperator<T extends Helper> extends BaseComparisonOperator<T>,
        BaseComparisonOperatorImpl<T, HavingDatum>,
        ToColumnComparisonOperator<T>,
        ToColumnComparisonOperatorImpl<T, HavingDatum>,
        ToColumnCallbackComparisonOperator<T>,
        ToColumnCallbackComparisonOperatorImpl<T>,
        ToSubQueryComparisonOperator<T>,
        ToSubQueryComparisonOperatorImpl<T, HavingDatum>,
        ToSqlPartBuilderComparisonOperator<T, HavingSqlPartDatumBuilder>,
        ToSqlPartBuilderComparisonOperatorImpl<T, HavingDatum, HavingSqlPartDatumBuilder> {

}