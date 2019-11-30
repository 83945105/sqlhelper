package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.callback.OnCallback;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.engine.Engine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface OnCallbackEngine<TO extends OnHelper<TO>, R> extends Engine {

    /**
     * use callback to add where sql data
     *
     * @param onCallback {@link OnCallback}
     * @return R
     */
    R on(OnCallback<TO> onCallback);

    /**
     * use callback to add assign class on sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback);

    /**
     * use callback to add assign class on sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R on(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return on(tableHelperClass, null, onJoinCallback);
    }
}