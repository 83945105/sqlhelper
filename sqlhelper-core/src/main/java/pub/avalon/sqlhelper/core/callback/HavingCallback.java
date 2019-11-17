package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface HavingCallback<TH extends HavingHelper<TH>> {

    TH apply(TH table);
}