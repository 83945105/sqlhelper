package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * @author 白超
 * @date 2019/5/18
 */
@FunctionalInterface
public interface WhereJoinLinkerCallback<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>,
        SW extends WhereSqlModel<SW>> {

    /**
     * 接收处理条件
     *
     * @param condition 连接方式
     * @param joinTable 连接表
     * @return {@link WhereLinker}
     */
    WhereLinker<T, TO, TC, TW, TG, TS> apply(WhereLinker<T, TO, TC, TW, TG, TS> condition, SW joinTable);
}