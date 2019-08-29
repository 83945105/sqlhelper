package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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
    R sort(SortCallback<TS> sortCallback);

    /**
     * use callback to add assign class sort sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param sortCallback     {@link SortCallback}
     * @return R
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback);

    /**
     * use callback to add assign class sort sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param sortCallback     {@link SortCallback}
     * @return R
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R sort(Class<S> tableHelperClass, SortCallback<SS> sortCallback) {
        return sort(tableHelperClass, null, sortCallback);
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableSortDatum execute(Class<F> tableHelperClass, String tableAlias, SortCallback<FS> sortCallback, SqlBuilderOptions sqlBuilderOptions) {
        return SortCallback.execute(tableHelperClass, tableAlias, sortCallback, sqlBuilderOptions);
    }
}