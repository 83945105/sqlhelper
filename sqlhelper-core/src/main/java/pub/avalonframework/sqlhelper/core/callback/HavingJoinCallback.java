package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.beans.HavingLinker;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HavingJoinCallback<TW extends HavingHelper<TW>, SW extends HavingHelper<SW>> {

    HavingLinker<TW> apply(HavingLinker<TW> having, SW joinTable, TW mainTable);
}