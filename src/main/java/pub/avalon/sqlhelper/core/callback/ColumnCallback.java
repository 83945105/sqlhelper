package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * 列回调
 *
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface ColumnCallback<T extends ColumnHelper<T>> {

    /**
     * 接收列助手
     *
     * @param table {@link ColumnHelper}
     * @return {@link ColumnHelper}
     */
    T apply(T table);

}
