package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnCallback<TW extends OnHelper<TW>> {

    OnLinker<TW> apply(OnLinker<TW> on, TW mainTable);
}