package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排序引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface SortEngine<TS extends SortHelper<TS>, R extends SortEngine<TS, R>> {

    R sort(SortHelper<?>... sortHelpers);

    R sort(SortCallback<TS> callback);

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R sort(Class<S> tableHelperClass, SortCallback<SS> callback) {
        return sort(tableHelperClass, null, callback);
    }

    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback);

    static List<TableSortDatum> execute(SortHelper<?>... sortHelpers) {
        if (sortHelpers == null || sortHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(sortHelpers).map(sortHelper -> sortHelper.execute()).collect(Collectors.toList());
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
