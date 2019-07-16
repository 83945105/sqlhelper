package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface WhereCallback<TW extends WhereHelper<TW>> {

    /**
     * 接收条件连接器
     *
     * @param condition {@link WhereLinker}
     * @param mainTable 主表
     * @return {@link WhereLinker}
     */
    WhereLinker<TW> apply(WhereLinker<TW> condition, TW mainTable);
}
