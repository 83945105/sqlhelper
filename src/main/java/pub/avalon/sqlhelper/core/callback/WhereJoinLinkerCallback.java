package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/5/18
 */
@FunctionalInterface
public interface WhereJoinLinkerCallback<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>,
        SW extends WhereHelper<SW>> {

    /**
     * 接收条件连接器
     *
     * @param condition {@link WhereLinker}
     * @param joinTable 连接表
     * @return {@link WhereLinker}
     */
    WhereLinker<T, TO, TC, TW, TG, TS> apply(WhereLinker<T, TO, TC, TW, TG, TS> condition, SW joinTable);
}