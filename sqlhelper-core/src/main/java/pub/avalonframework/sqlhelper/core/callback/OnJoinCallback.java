package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.OnLinker;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnJoinCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    OnLinker<TO> apply(OnLinker<TO> on, SO joinTable, TO mainTable);
}