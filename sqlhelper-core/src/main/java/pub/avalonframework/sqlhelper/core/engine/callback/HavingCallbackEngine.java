package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.callback.HavingCallback;
import pub.avalonframework.sqlhelper.core.engine.Engine;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

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