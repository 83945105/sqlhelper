package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.beans.OnAndOr;
import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
@FunctionalInterface
public interface JoinCallback<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> {

    OnLinker<TJ, SJ> apply(OnLinker<TJ, SJ> on, SJ joinTable, TJ mainTable);

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
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, Class<F> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, JoinCallback<FJ, EJ> joinCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        FJ fj = BeanUtils.tableHelper(mainTableHelperClass).newJoinHelper(mainTableAlias);
        return execute(joinType, fj, joinTableName, joinTableHelperClass, joinTableAlias, joinCallback, sqlBuilderOptions);
    }

    static <FJ extends JoinHelper<FJ>,
            E extends TableHelper<E, EJ, EC, EW, EG, EH, ES>,
            EJ extends JoinHelper<EJ>,
            EC extends ColumnHelper<EC>,
            EW extends WhereHelper<EW>,
            EG extends GroupHelper<EG>,
            EH extends HavingHelper<EH>,
            ES extends SortHelper<ES>> JoinTableDatum execute(JoinType joinType, FJ mainJoinHelper, String joinTableName, Class<E> joinTableHelperClass, String joinTableAlias, JoinCallback<FJ, EJ> joinCallback, SqlBuilderOptions sqlBuilderOptions) {
        E e = BeanUtils.tableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? e.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        JoinTableDatum joinTableDatum = new JoinTableDatum(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        EJ ej = BeanUtils.tableHelper(joinTableHelperClass).newJoinHelper(joinTableAlias);
        // 设置配置开始
        ej.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        OnLinker<FJ, EJ> onLinker = new OnAndOr<>();
        // 设置配置开始
        mainJoinHelper.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        if (joinCallback == null) {
            return joinTableDatum;
        }
        OnLinker<FJ, EJ> linker = joinCallback.apply(onLinker, ej, mainJoinHelper);
        List<OnDataLinker> onDataLinkers = linker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return joinTableDatum;
        }
        joinTableDatum.setTableOnDatum(new TableOnDatum(joinTableAlias, onDataLinkers));
        return joinTableDatum;
    }
}