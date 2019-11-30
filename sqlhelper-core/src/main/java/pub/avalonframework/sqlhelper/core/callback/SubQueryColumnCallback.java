package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SubQueryColumnCallback<TC extends ColumnHelper<TC>> {

    SelectSqlBuilderResult apply(TC parentTable);
}