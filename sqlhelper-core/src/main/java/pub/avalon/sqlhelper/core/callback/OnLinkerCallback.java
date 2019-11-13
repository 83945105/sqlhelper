package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnLinkerCallback<TO extends OnHelper<TO>> {

    OnLinker<TO> apply(OnLinker<TO> on);
}