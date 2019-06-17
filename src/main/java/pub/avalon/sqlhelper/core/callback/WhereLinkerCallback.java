package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface WhereLinkerCallback<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    /**
     * 接收条件连接器
     *
     * @param condition {@link WhereLinker}
     * @return {@link WhereLinker}
     */
    WhereLinker<T, TO, TC, TW, TG, TS> apply(WhereLinker<T, TO, TC, TW, TG, TS> condition);
}
