package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SubQueryColumnCallback<TC extends ColumnHelper<TC>> {

    SelectSqlBuilderResult apply(TC parentTable);
}