package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.JoinHelper;

/**
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface OnLinkerCallback<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> {

    /**
     * 接收On条件连接器
     *
     * @param on {@link OnLinker}
     * @return {@link OnLinker}
     */
    OnLinker<TJ, SJ> apply(OnLinker<TJ, SJ> on);

}
