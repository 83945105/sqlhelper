package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * 连接查询On条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface OnColumnCallback<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> {

    /**
     * 接收处理On条件模组
     *
     * @param table On条件模组
     * @return On条件构建器
     */
    TC apply(TC table);

}
