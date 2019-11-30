package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface OnEngine<R> extends Engine {

    /**
     * add on sql data
     *
     * @param onHelpers {@link OnHelper}
     * @return R
     */
    R on(OnHelper<?>... onHelpers);
}