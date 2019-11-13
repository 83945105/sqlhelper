package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface GroupCallback<T extends GroupHelper<T>> {

    T apply(T table);
}