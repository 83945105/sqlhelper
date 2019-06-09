package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.SortHelper;

/**
 * sort 回调
 *
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface SortCallback<T extends SortHelper<T>> {

    /**
     * 接收 sort sql模组
     *
     * @param table
     * @return
     */
    T apply(T table);

}
