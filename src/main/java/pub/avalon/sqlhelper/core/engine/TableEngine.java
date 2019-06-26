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
        TS extends SortHelper<TS>> extends
        ColumnEngine<T, TO, TC, TW, TG, TS>,
        JoinEngine<T, TO, TC, TW, TG, TS>,
        WhereEngine<T, TO, TC, TW, TG, TS>,
        GroupEngine<T, TO, TC, TW, TG, TS>,
        SortEngine<T, TO, TC, TW, TG, TS>,
        LimitEngine<T, TO, TC, TW, TG, TS> {
}
