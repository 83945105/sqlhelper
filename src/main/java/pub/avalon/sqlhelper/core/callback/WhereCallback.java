package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * Where 回调
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface WhereCallback<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> {

    /**
     * 接收处理条件
     *
     * @param condition 连接方式
     * @param mainTable 主表
     * @return {@link pub.avalon.sqlhelper.core.beans.WhereLinker}
     */
    WhereLinker<T, TO, TC, TW, TG, TS> apply(WhereLinker<T, TO, TC, TW, TG, TS> condition, TW mainTable);
}
