package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.callback.SortCallback;
import pub.avalonframework.sqlhelper.core.engine.Engine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SortCallbackEngine<TS extends SortHelper<TS>, R> extends Engine {

    /**
     * use callback to add sort sql data
     *
     * @param sortCallback {@link SortCallback}
     * @return R
     */
    R orderBy(SortCallback<TS> sortCallback);

    /**
     * use callback to add assign class sort sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param sortCallback     {@link SortCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback);

    /**
     * use callback to add assign class sort sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param sortCallback     {@link SortCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R orderBy(Class<S> tableHelperClass, SortCallback<SS> sortCallback) {
        return orderBy(tableHelperClass, null, sortCallback);
    }
}