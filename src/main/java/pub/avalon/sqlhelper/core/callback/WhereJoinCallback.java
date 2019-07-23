package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.WhereAndOr;
import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author 白超
 * @date 2019/5/18
 */
public interface WhereJoinCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    /**
     * 接收条件连接器
     *
     * @param condition {@link WhereLinker}
     * @param joinTable 连接表
     * @param mainTable 主表
     * @return {@link WhereLinker}
     */
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
        if (whereJoinCallback == null) {
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
        WhereLinker<FW> whereLinker = whereJoinCallback.apply(new WhereAndOr<>(), ew, fw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(joinTableAlias, whereDataLinkers);
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
        E e = BeanUtils.tableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? e.getTableAlias() : joinTableAlias;
        EW ew = e.newWhereHelper(joinTableAlias);
        // 设置配置开始
        ew.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        // 设置配置开始
        mainWhereHelper.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        WhereLinker<FW> whereLinker = whereJoinCallback.apply(new WhereAndOr<>(), ew, mainWhereHelper);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDatum(joinTableAlias, whereDataLinkers);
    }
}