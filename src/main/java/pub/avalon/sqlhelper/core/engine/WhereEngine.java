package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.WhereAndOr;
import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface WhereEngine<TW extends WhereHelper<TW>, R extends WhereEngine<TW, R>> {

    R where(WhereHelper<?>... whereHelpers);

    R where(WhereCallback<TW> whereCallback);

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, WhereJoinCallback<TW, SW> whereCallback) {
        return where(tableHelperClass, null, whereCallback);
    }

    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereCallback);

    static List<TableWhereDatum> execute(WhereHelper<?>... whereHelpers) {
        if (whereHelpers == null || whereHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(whereHelpers).map(whereHelper -> whereHelper.execute()).collect(Collectors.toList());
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableWhereDatum execute(Class<F> tableHelperClass, String tableAlias, WhereCallback<FW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        return WhereCallback.execute(tableHelperClass, tableAlias, whereCallback, sqlBuilderOptions);
    }

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
            ES extends SortHelper<ES>> TableWhereDatum execute(Class<F> mainTableHelperClass, String mainTableAlias, Class<E> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<FW, EW> whereCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (whereCallback == null) {
            return null;
        }
        E e = BeanUtils.tableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        EW ew = e.newWhereHelper(joinTableAlias);
        // 设置配置开始
        ew.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        FW fw = BeanUtils.tableHelper(mainTableHelperClass).newWhereHelper(mainTableAlias);
        // 设置配置开始
        fw.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        WhereLinker<FW> whereLinker = whereCallback.apply(new WhereAndOr<>(), ew, fw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(joinTableAlias, whereDataLinkers);
    }
}
