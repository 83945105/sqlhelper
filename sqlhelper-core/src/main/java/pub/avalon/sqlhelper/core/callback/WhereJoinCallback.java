package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.WhereAndOr;
import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.List;

/**
 * @author baichao
 */
public interface WhereJoinCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition, SW joinTable, TW mainTable);

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>,
            E extends TableHelper<E, EJ, EC, EW, EG, EH, ES>,
            EJ extends JoinHelper<EJ>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> TableWhereDatum execute(Class<F> mainTableHelperClass, String mainTableAlias, Class<E> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<FW, EW> whereJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        F f = HelperManager.defaultTableHelper(mainTableHelperClass);
        FW fw = f.newWhereHelper(mainTableAlias);
        return execute(fw, joinTableHelperClass, joinTableAlias, whereJoinCallback, sqlBuilderOptions);
    }

    static <FW extends WhereHelper<FW>,
            E extends TableHelper<E, EJ, EC, EW, EG, EH, ES>,
            EJ extends JoinHelper<EJ>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> TableWhereDatum execute(FW mainWhereHelper, Class<E> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<FW, EW> whereJoinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (whereJoinCallback == null) {
            return null;
        }
        mainWhereHelper.setSqlBuilderOptions(sqlBuilderOptions);
        E e = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        EW ew = e.newWhereHelper(joinTableAlias);
        ew.setSqlBuilderOptions(sqlBuilderOptions);
        WhereLinker<FW> whereLinker = whereJoinCallback.apply(new WhereAndOr<>(), ew, mainWhereHelper);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(joinTableAlias, whereDataLinkers);
    }
}