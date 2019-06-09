package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/5/18
 */
public interface WhereJoinCallback<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>,
        SW extends WhereHelper<SW>> {

    /**
     * 接收处理条件
     *
     * @param condition 连接方式
     * @param joinTable 连接表
     * @param mainTable 主表
     * @return {@link pub.avalon.sqlhelper.core.beans.WhereLinker}
     */
    WhereLinker<T, TO, TC, TW, TG, TS> apply(WhereLinker<T, TO, TC, TW, TG, TS> condition, SW joinTable, TW mainTable);
}