package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface WhereColumnCallback<TC extends ColumnHelper<TC>> {

    /**
     * 接收条件助手
     *
     * @param table {@link ColumnHelper}
     * @return {@link ColumnHelper}
     */
    TC apply(TC table);

}
