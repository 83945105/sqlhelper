package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.WhereLinker;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereJoinLinkerCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition, SW joinTable);
}