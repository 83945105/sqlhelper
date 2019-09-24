package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param whereCallback    {@link WhereCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereCallback);

    /**
     * use callback to add assign class where sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param whereCallback    {@link WhereCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, WhereJoinCallback<TW, SW> whereCallback) {
        return where(tableHelperClass, null, whereCallback);
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableWhereDatum execute(Class<F> tableHelperClass, String tableAlias, WhereCallback<FW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        return WhereCallback.execute(tableHelperClass, tableAlias, whereCallback, sqlBuilderOptions);
    }

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>,
            E extends TableHelper<E, EJ, EC, EW, EG, EH, ES>,
            EJ extends OnHelper<EJ>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> TableWhereDatum execute(Class<F> mainTableHelperClass, String mainTableAlias, Class<E> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<FW, EW> whereJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        return WhereJoinCallback.execute(mainTableHelperClass, mainTableAlias, joinTableHelperClass, joinTableAlias, whereJoinCallback, sqlBuilderOptions);
    }
}