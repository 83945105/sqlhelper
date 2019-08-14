package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.GroupHelper;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface GroupEngine<R> extends Engine {

    R group(GroupHelper<?>... groupHelpers);

}
