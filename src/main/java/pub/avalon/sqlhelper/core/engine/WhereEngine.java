package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.WhereHelper;

/**
 * 条件引擎
 *
 * @author baichao
 * @since 2018/7/10
 */
public interface WhereEngine<R> extends Engine {

    R where(WhereHelper<?>... whereHelpers);

}
