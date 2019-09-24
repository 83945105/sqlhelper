package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.beans.OnAndOr;
import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.List;

/**
 * @author baichao
 */
@FunctionalInterface
public interface JoinCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    OnLinker<TO, SO> apply(OnLinker<TO, SO> on, SO joinTable, TO mainTable);

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
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, Class<F> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, JoinCallback<FO, EJ> joinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        F f = HelperManager.defaultTableHelper(mainTableHelperClass);
        FO fj = f.newJoinHelper(mainTableAlias);
        return execute(joinType, fj, joinTableName, joinTableHelperClass, joinTableAlias, joinCallback, sqlBuilderOptions);
    }

    static <FO extends OnHelper<FO>,
            E extends TableHelper<E, EJ, EC, EW, EG, EH, ES>,
            EJ extends OnHelper<EJ>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, FO mainJoinHelper, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, JoinCallback<FO, EJ> joinCallback, SqlBuilderOptions sqlBuilderOptions) {
        E e = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? e.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        EJ ej = e.newJoinHelper(joinTableAlias);
        ej.setSqlBuilderOptions(sqlBuilderOptions);
        OnLinker<FO, EJ> onLinker = new OnAndOr<>();
        mainJoinHelper.setSqlBuilderOptions(sqlBuilderOptions);
        if (joinCallback == null) {
            return joinTableDatum;
        }
        OnLinker<FO, EJ> linker = joinCallback.apply(onLinker, ej, mainJoinHelper);
        List<OnDataLinker> onDataLinkers = linker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return joinTableDatum;
        }
        joinTableDatum.setTableOnDatum(new TableOnDatum(joinTableAlias, onDataLinkers));
        return joinTableDatum;
    }
}