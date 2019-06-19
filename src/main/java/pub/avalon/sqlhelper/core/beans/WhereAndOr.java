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
public final class WhereAndOr<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> implements WhereLinker<T, TO, TC, TW, TG, TS> {

    private List<WhereDataLinker> whereDataLinkers = new ArrayList<>();

    @Override
    public List<WhereDataLinker> takeoutWhereDataLinkers() {
        List<WhereDataLinker> whereDataLinkers = this.whereDataLinkers;
        this.whereDataLinkers = new ArrayList<>();
        return whereDataLinkers;
    }

    @Override
    public WhereAndOr<T, TO, TC, TW, TG, TS> and(WhereHelper<?> whereHelper) {
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
    public WhereAndOr<T, TO, TC, TW, TG, TS> and(WhereLinkerCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return this;
        }
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereAndOr<>());
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
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereAndOr<T, TO, TC, TW, TG, TS> and(Class<S> tableHelperClass,
                                                                             String alias,
                                                                             WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        SW sw = BeanUtils.tableHelper(tableHelperClass).newWhereHelper();
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereAndOr<>(), sw);
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
    public WhereAndOr<T, TO, TC, TW, TG, TS> or(WhereHelper<?> whereHelper) {
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
    public WhereAndOr<T, TO, TC, TW, TG, TS> or(WhereLinkerCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return this;
        }
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereAndOr<>());
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
     * @param alias            目标条件别名
     * @param callback         条件连接器回调
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereAndOr<T, TO, TC, TW, TG, TS> or(Class<S> tableHelperClass,
                                                                            String alias,
                                                                            WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        SW sw = BeanUtils.tableHelper(tableHelperClass).newWhereHelper();
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereAndOr<>(), sw);
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
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereAndOr<T, TO, TC, TW, TG, TS> or(Class<S> tableHelperClass,
                                                                            WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return or(tableHelperClass, null, callback);
    }

}
