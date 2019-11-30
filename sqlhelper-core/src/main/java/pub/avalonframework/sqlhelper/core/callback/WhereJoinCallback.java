package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.WhereLinker;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface WhereJoinCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition, SW joinTable, TW mainTable);
}