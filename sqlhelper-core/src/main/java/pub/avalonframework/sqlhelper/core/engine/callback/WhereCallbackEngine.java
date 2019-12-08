package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.callback.WhereCallback;
import pub.avalonframework.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalonframework.sqlhelper.core.engine.Engine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface WhereCallbackEngine<TW extends WhereHelper<TW>, R> extends Engine {

    /**
     * use callback to add where sql data
     *
     * @param whereCallback {@link WhereCallback}
     * @return R
     */
    R where(WhereCallback<TW> whereCallback);

    /**
     * use callback to add assign class where sql data
     *
     * @param tableHelperClass  extends {@link TableHelper} class
     * @param tableAlias        table alias
     * @param whereJoinCallback {@link WhereJoinCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback);

    /**
     * use callback to add assign class where sql data
     *
     * @param tableHelperClass  extends {@link TableHelper} class
     * @param whereJoinCallback {@link WhereJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, WhereJoinCallback<TW, SW> whereJoinCallback) {
        return where(tableHelperClass, null, whereJoinCallback);
    }
}