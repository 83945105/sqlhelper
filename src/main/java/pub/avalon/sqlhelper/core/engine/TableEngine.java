package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.*;

/**
 * 表引擎
 *
 * @author 白超
 * @date 2019/6/26
 */
public interface TableEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>,
        R extends TableEngine<T, TJ, TC, TW, TG, TH, TS, R>> extends
        ColumnEngine<TC, R>,
        JoinEngine<TJ, R>,
        WhereEngine<TW, R>,
        GroupEngine<TG, R>,
        SortEngine<TS, R>,
        LimitEngine<R> {
}
