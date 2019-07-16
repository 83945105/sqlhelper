package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.OnHelper;

/**
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface OnLinkerCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    /**
     * 接收On条件连接器
     *
     * @param on {@link OnLinker}
     * @return {@link OnLinker}
     */
    OnLinker<TO, SO> apply(OnLinker<TO, SO> on);

}
