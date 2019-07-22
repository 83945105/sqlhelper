package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.JoinHelper;

/**
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface JoinCallback<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> {

    /**
     * 接收条件连接器
     *
     * @param on        {@link OnLinker}
     * @param joinTable 连接表
     * @param mainTable 主表
     * @return {@link OnLinker}
     */
    OnLinker<TJ, SJ> apply(OnLinker<TJ, SJ> on, SJ joinTable, TJ mainTable);

}
