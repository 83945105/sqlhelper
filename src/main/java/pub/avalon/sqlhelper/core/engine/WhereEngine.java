package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface WhereEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    TableEngine<T, TO, TC, TW, TG, TS> where(WhereCallback<T, TO, TC, TW, TG, TS> callback);

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<T, TO, TC, TW, TG, TS, SW> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, WhereJoinCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return where(tableHelperClass, null, callback);
    }

}
