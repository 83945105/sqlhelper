package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * on 回调
 *
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface OnLinkerCallback<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>,
        S extends TableHelper<S, SO, SC, SW, SG, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SS extends SortHelper<SS>> {

    /**
     * 接收 on sql模组
     *
     * @param on        连接方式
     * @return
     */
    OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> apply(OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> on);

}
