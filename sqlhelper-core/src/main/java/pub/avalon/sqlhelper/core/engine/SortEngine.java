package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.SortHelper;

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