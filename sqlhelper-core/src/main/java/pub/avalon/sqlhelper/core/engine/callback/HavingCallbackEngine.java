package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.callback.HavingCallback;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HavingCallbackEngine<TH extends HavingHelper<TH>, R> extends Engine {

    /**
     * use callback to add column sql data
     *
     * @param havingCallback {@link HavingCallback}
     * @return R
     */
    R having(HavingCallback<TH> havingCallback);
}