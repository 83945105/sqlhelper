package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereColumnCallback<TC extends ColumnHelper<TC>> {

    TC apply(TC table);
}
