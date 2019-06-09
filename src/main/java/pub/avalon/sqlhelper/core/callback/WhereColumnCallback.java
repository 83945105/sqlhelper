package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * Where条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface WhereColumnCallback<TC extends ColumnHelper<TC>> {

    /**
     * 接收处理Where条件模组
     *
     * @param table Where条件模组
     * @return Where条件构建器
     */
    TC apply(TC table);

}
