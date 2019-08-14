package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.JoinHelper;

/**
 * @author baichao
 * @date 2019/5/16
 */
@FunctionalInterface
public interface OnLinkerCallback<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> {

    OnLinker<TJ, SJ> apply(OnLinker<TJ, SJ> on);

}
