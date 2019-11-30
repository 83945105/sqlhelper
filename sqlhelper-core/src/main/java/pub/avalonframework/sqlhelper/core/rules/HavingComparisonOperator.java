package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.HavingDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.impl.*;

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