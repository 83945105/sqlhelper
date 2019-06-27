package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface LimitEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>, R extends LimitEngine<T, TO, TC, TW, TG, TS, R>> {

    R limitTop(Integer num);

    R limitOne();

    R limit(LimitHandler limit);

    R limit(Integer start, Integer end);

    R limit(Integer total, Integer currentPage, Integer pageSize);

}
