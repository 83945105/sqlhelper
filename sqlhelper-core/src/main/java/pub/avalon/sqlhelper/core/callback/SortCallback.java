package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SortCallback<T extends SortHelper<T>> {

    T apply(T table);
}