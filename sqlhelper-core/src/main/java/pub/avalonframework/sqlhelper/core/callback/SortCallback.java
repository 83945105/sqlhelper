package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SortCallback<T extends SortHelper<T>> {

    T apply(T table);
}