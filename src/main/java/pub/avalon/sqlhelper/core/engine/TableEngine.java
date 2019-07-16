package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.*;

/**
 * 表引擎
 *
 * @author 白超
 * @date 2019/6/26
 */
public interface TableEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>,
        R extends TableEngine<T, TO, TC, TW, TG, TS, R>> extends
        ColumnEngine<TC, R>,
        JoinEngine<T, TO, TC, TW, TG, TS, R>,
        WhereEngine<TW, R>,
        GroupEngine<TG, R>,
        SortEngine<TS, R>,
        LimitEngine<T, TO, TC, TW, TG, TS, R> {
}
