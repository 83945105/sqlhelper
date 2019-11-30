package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.OnLinker;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnLinkerCallback<TO extends OnHelper<TO>> {

    OnLinker<TO> apply(OnLinker<TO> on);
}