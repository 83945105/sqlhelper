package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
public interface SortEngine<R> extends Engine {

    /**
     * add sort sql data
     *
     * @param sortHelpers {@link SortHelper}
     * @return R
     */
    R orderBy(SortHelper<?>... sortHelpers);
}