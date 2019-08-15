package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.GroupHelper;

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
    R group(GroupHelper<?>... groupHelpers);
}