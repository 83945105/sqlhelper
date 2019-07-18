package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * and or 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereAndOr<TW extends WhereHelper<TW>> implements WhereLinker<TW> {

    private List<WhereDataLinker> whereDataLinkers = new ArrayList<>();

    @Override
    public List<WhereDataLinker> takeoutWhereDataLinkers() {
        List<WhereDataLinker> whereDataLinkers = this.whereDataLinkers;
        this.whereDataLinkers = new ArrayList<>();
        return whereDataLinkers;
    }

    @Override
    public WhereAndOr<TW> and(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        Set<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    @Override
    public WhereAndOr<TW> and(WhereLinkerCallback<TW> callback) {
        if (callback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = callback.apply(new WhereAndOr<>());
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        whereDataLinker.setWhereDataLinkers(whereDataLinkers);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> and(Class<S> tableHelperClass,
                                                          String tableAlias,
                                                          WhereJoinLinkerCallback<TW, SW> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereLinker<TW> whereLinker = callback.apply(new WhereAndOr<>(), sw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        whereDataLinker.setWhereDataLinkers(whereDataLinkers);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param whereHelper 条件助手
     * @return {@link WhereAndOr}
     */
    public WhereAndOr<TW> or(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        Set<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param callback 条件连接器回调
     * @return {@link pub.avalon.sqlhelper.core.callback.WhereCallback}
     */
    public WhereAndOr<TW> or(WhereLinkerCallback<TW> callback) {
        if (callback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = callback.apply(new WhereAndOr<>());
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        whereDataLinker.setWhereDataLinkers(whereDataLinkers);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param tableHelperClass 目标条件类
     * @param tableAlias       目标条件别名
     * @param callback         条件连接器回调
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> or(Class<S> tableHelperClass,
                                                         String tableAlias,
                                                         WhereJoinLinkerCallback<TW, SW> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereLinker<TW> whereLinker = callback.apply(new WhereAndOr<>(), sw);
        List<WhereDataLinker> whereDataLinkers = whereLinker.takeoutWhereDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        whereDataLinker.setWhereDataLinkers(whereDataLinkers);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param tableHelperClass 目标条件类
     * @param callback         条件连接器回调
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> or(Class<S> tableHelperClass,
                                                         WhereJoinLinkerCallback<TW, SW> callback) {
        return or(tableHelperClass, null, callback);
    }

}
