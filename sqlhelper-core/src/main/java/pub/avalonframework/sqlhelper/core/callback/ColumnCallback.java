package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface ColumnCallback<TC extends ColumnHelper<TC>> {

    TC apply(TC table);
}