package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
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
        List<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    @Override
    public WhereAndOr<TW> and(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = whereLinkerCallback.apply(new WhereAndOr<>());
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
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> and(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        if (whereJoinLinkerCallback == null) {
            return this;
        }
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereLinker<TW> whereLinker = whereJoinLinkerCallback.apply(new WhereAndOr<>(), sw);
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
     * or
     *
     * @param whereHelper {@link WhereHelper}
     * @return {@link WhereAndOr}
     */
    public WhereAndOr<TW> or(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        List<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param whereLinkerCallback {@link WhereLinkerCallback}
     * @return {@link WhereAndOr}
     */
    public WhereAndOr<TW> or(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = whereLinkerCallback.apply(new WhereAndOr<>());
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
     * or
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param tableAlias              target {@link TableHelper} alias
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> or(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        if (whereJoinLinkerCallback == null) {
            return this;
        }
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereLinker<TW> whereLinker = whereJoinLinkerCallback.apply(new WhereAndOr<>(), sw);
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
     * or
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> or(Class<S> tableHelperClass, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        return or(tableHelperClass, null, whereJoinLinkerCallback);
    }
}