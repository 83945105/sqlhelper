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
public interface SortEngine<TS extends SortHelper<TS>, R extends SortEngine<TS, R>> {

    R sort(SortHelper<?>... sortHelpers);

    R sort(SortCallback<TS> callback);

    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback);

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R sort(Class<S> tableHelperClass, SortCallback<SS> callback) {
        return sort(tableHelperClass, null, callback);
    }

}
