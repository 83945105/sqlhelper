package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 排序引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface SortEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortSqlModels);

    SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback);

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, SortCallback<SS> callback) {
        return sort(tableHelperClass, null, callback);
    }

}
