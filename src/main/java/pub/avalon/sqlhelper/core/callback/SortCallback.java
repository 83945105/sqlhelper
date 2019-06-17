package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.SortHelper;

/**
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface SortCallback<T extends SortHelper<T>> {

    /**
     * 接收排序助手
     *
     * @param table {@link SortHelper}
     * @return {@link SortHelper}
     */
    T apply(T table);

}
