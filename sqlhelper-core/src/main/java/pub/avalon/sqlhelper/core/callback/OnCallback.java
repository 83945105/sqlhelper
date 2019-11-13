package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    OnLinker<TO, SO> apply(OnLinker<TO, SO> on, SO joinTable, TO mainTable);
}