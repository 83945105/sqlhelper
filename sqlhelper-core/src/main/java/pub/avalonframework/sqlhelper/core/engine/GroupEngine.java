package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
public interface GroupEngine<R> extends Engine {

    /**
     * add group sql data
     *
     * @param groupHelpers extends {@link GroupHelper} objects
     * @return R
     */
    R groupBy(GroupHelper<?>... groupHelpers);
}