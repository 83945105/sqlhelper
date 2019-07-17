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
public interface WhereEngine<TW extends WhereHelper<TW>, R extends WhereEngine<TW, R>> {

    R where(WhereCallback<TW> callback);

    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback);

    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, WhereJoinCallback<TW, SW> callback) {
        return where(tableHelperClass, null, callback);
    }

}
