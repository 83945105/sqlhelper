package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * @author 白超
 * @date 2019/5/18
 */
public interface WhereJoinCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    /**
     * 接收条件连接器
     *
     * @param condition {@link WhereLinker}
     * @param joinTable 连接表
     * @param mainTable 主表
     * @return {@link WhereLinker}
     */
    WhereLinker<TW> apply(WhereLinker<TW> condition, SW joinTable, TW mainTable);
}