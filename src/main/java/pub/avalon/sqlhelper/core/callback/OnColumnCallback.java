package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 * @since 2018/7/10
 */
@FunctionalInterface
public interface OnColumnCallback<TC extends ColumnHelper<TC>> {

    TC apply(TC table);

}
