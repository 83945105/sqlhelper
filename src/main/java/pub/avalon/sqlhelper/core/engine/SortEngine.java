package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.SortHelper;

/**
 * 排序引擎
 *
 * @author baichao
 * @since 2018/7/10
 */
public interface SortEngine<R> extends Engine {

    R sort(SortHelper<?>... sortHelpers);

}
