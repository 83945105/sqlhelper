package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereJoinLinkerCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition, SW joinTable);
}