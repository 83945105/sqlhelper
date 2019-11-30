package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface GroupCallback<T extends GroupHelper<T>> {

    T apply(T table);
}