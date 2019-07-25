package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQueryColumnCallback<TC extends ColumnHelper<TC>> {

    SqlBuilderResult apply(TC parentTable);

}
