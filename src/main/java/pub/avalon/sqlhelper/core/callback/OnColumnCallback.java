package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.modelbuilder.ColumnSqlModel;

/**
 * 连接查询On条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface OnColumnCallback<TC extends ColumnSqlModel<TC>> {

    /**
     * 接收处理On条件模组
     *
     * @param table On条件模组
     * @return On条件构建器
     */
    TC apply(TC table);

}
