package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface HavingCallback<TH extends HavingHelper<TH>> {

    TH apply(TH table);
}