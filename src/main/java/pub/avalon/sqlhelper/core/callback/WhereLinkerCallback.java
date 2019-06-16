package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Where Linker 回调
 *
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
     * 接收处理条件
     *
     * @param condition 连接方式
     * @return {@link WhereLinker}
     */
    WhereLinker<T, TO, TC, TW, TG, TS> apply(WhereLinker<T, TO, TC, TW, TG, TS> condition);
}