package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.OnLinker;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnCallback<TW extends OnHelper<TW>> {

    OnLinker<TW> apply(OnLinker<TW> on, TW mainTable);
}