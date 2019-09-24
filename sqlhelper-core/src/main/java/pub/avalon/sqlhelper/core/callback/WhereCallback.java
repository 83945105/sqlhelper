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
@FunctionalInterface
public interface WhereCallback<TW extends WhereHelper<TW>> {

    WhereLinker<TW> apply(WhereLinker<TW> condition, TW mainTable);

    static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableWhereDatum execute(Class<T> tableHelperClass, String tableAlias, WhereCallback<TW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TW tw = t.newWhereHelper(tableAlias);
        return execute(tw, whereCallback, sqlBuilderOptions);
    }

    static <TW extends WhereHelper<TW>> TableWhereDatum execute(TW whereHelper, WhereCallback<TW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (whereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereCallback == null) {
            return null;
        }
        whereHelper.setSqlBuilderOptions(sqlBuilderOptions);
        WhereLinker<TW> whereLinker = whereCallback.apply(new WhereAndOr<>(), whereHelper);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(whereHelper.getTableAlias(), whereDataLinkers);
    }
}