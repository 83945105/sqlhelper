package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.helper.JoinHelper;

import java.util.List;

/**
 * On条件连接器
 *
 * @author baichao
 * @since 2018/7/10
 */
public interface OnLinker<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> {

    /**
     * 取出On数据连接器
     * 取出后清空
     *
     * @return {@link java.util.ArrayList}
     */
    List<OnDataLinker> takeoutOnDataLinkers();

    /**
     * 且
     *
     * @param onHelper On助手
     * @return {@link OnAndOr}
     */
    OnAndOr<TJ, SJ> and(JoinHelper<?> onHelper);

    /**
     * 且
     *
     * @param callback On条件连接器回调
     * @return On条件连接器 {@link OnAndOr}
     */
    OnAndOr<TJ, SJ> and(OnLinkerCallback<TJ, SJ> callback);

}
