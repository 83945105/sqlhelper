package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.modelbuilder.GroupSqlModel;

/**
 * group 回调
 *
 * @author 白超
 * @date 2019/5/18
 */
@FunctionalInterface
public interface GroupCallback<T extends GroupSqlModel<T>> {

    /**
     * 接收 group sql模组
     *
     * @param table
     * @return
     */
    T apply(T table);

}
