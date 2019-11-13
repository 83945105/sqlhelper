package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereCallback<TW extends WhereHelper<TW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition, TW mainTable);
}