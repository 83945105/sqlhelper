package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.callback.HavingCallback;
import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
import pub.avalonframework.sqlhelper.core.engine.Engine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface HavingCallbackEngine<TH extends HavingHelper<TH>, R> extends Engine {

    /**
     * use callback to add having sql data
     *
     * @param havingCallback {@link HavingCallback}
     * @return R
     */
    R having(HavingCallback<TH> havingCallback);

    /**
     * use callback to add assign class having sql data
     *
     * @param tableHelperClass   extends {@link TableHelper} class
     * @param tableAlias         table alias
     * @param havingJoinCallback {@link HavingJoinCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback);

    /**
     * use callback to add assign class having sql data
     *
     * @param tableHelperClass   extends {@link TableHelper} class
     * @param havingJoinCallback {@link HavingJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R having(Class<S> tableHelperClass, HavingJoinCallback<TH, SH> havingJoinCallback) {
        return having(tableHelperClass, null, havingJoinCallback);
    }
}