package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HavingEngine<R> extends Engine {

    /**
     * add having sql data
     *
     * @param havingHelpers {@link HavingHelper}
     * @return R
     */
    R having(HavingHelper<?>... havingHelpers);
}