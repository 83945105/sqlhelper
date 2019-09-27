package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnAndOr;
import pub.avalon.sqlhelper.core.beans.OnLinker;
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
public interface OnCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    OnLinker<TO, SO> apply(OnLinker<TO, SO> on, SO joinTable, TO mainTable);

    static <F extends TableHelper<F, FO, FC, FW, FG, FH, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>,
            E extends TableHelper<E, EO, EC, EW, EG, EH, ES>,
            EO extends OnHelper<EO>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> TableOnDatum execute(Class<F> mainTableHelperClass, String mainTableAlias, Class<E> joinTableHelperClass, String joinTableAlias, OnCallback<FO, EO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (joinTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        F f = HelperManager.newTableHelper(mainTableHelperClass);
        FO fo = f.newOnHelper(mainTableAlias == null ? f.getTableAlias() : mainTableAlias);
        E e = HelperManager.newTableHelper(joinTableHelperClass);
        EO eo = e.newOnHelper(joinTableAlias == null ? e.getTableAlias() : joinTableAlias);
        return execute(fo, eo, onCallback, sqlBuilderOptions);
    }

    static <FO extends OnHelper<FO>,
            EO extends OnHelper<EO>> TableOnDatum execute(FO mainOnHelper, EO joinOnHelper, OnCallback<FO, EO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (onCallback == null) {
            return null;
        }
        mainOnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        joinOnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        OnLinker<FO, EO> onLinker = onCallback.apply(new OnAndOr<>(), joinOnHelper, mainOnHelper);
        List<OnDataLinker> onDataLinkers = onLinker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return null;
        }
        return new TableOnDatum(joinOnHelper.getTableAlias(), onDataLinkers);
    }
}