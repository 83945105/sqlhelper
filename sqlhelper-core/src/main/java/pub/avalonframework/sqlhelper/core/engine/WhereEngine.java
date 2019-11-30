package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

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