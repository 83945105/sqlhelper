package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.WhereLinker;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereLinkerCallback<TW extends WhereHelper<TW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition);
}