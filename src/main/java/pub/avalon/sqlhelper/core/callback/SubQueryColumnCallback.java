package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * 子查询
 *
 * @author baichao
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQueryColumnCallback<TC extends ColumnHelper<TC>> {

    SelectSqlBuilderResult apply(TC parentTable);

}
