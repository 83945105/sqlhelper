package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * @author 白超
 * @date 2019/5/18
 */
@FunctionalInterface
public interface WhereJoinLinkerCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    /**
     * 接收条件连接器
     *
     * @param condition {@link WhereLinker}
     * @param joinTable 连接表
     * @return {@link WhereLinker}
     */
    WhereLinker<TW> apply(WhereLinker<TW> condition, SW joinTable);
}