package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.GroupHelper;

/**
 * @author 白超
 * @date 2019/5/18
 */
@FunctionalInterface
public interface GroupCallback<T extends GroupHelper<T>> {

    /**
     * 接收分组助手
     *
     * @param table {@link GroupHelper}
     * @return {@link GroupHelper}
     */
    T apply(T table);

}
