package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface WhereEngine<R> extends Engine {

    /**
     * add where sql data
     *
     * @param whereHelpers {@link WhereHelper}
     * @return R
     */
    R where(WhereHelper<?>... whereHelpers);
}