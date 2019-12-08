package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalonframework.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.WhereDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class WhereAndOr<TW extends WhereHelper<TW>> implements WhereLinker<TW> {

    private List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = new ArrayList<>();

    @Override
    public List<ComparisonSqlPartDataLinker> takeoutComparisonSqlPartDataLinkers() {
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = this.comparisonSqlPartDataLinkers;
        this.comparisonSqlPartDataLinkers = new ArrayList<>();
        return comparisonSqlPartDataLinkers;
    }

    @Override
    public WhereAndOr<TW> and(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        ComparisonSqlPartDataLinker comparisonSqlPartDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        List<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        comparisonSqlPartDataLinker.setComparisonSqlPartData(whereData);
        this.comparisonSqlPartDataLinkers.add(comparisonSqlPartDataLinker);
        return this;
    }

    @Override
    public WhereAndOr<TW> and(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = whereLinkerCallback.apply(new WhereAndOr<>());
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker whereDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
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
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker whereDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
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
        ComparisonSqlPartDataLinker whereDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        List<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setComparisonSqlPartData(whereData);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
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
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker whereDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
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
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker whereDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
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