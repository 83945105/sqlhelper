package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * column 回调
 *
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface ColumnCallback<T extends ColumnHelper<T>> {

    /**
     * 接收 column sql模组
     *
     * @param table
     * @return
     */
    T apply(T table);

}
