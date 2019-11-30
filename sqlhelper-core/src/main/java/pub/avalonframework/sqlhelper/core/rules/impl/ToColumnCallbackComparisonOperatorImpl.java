package pub.avalonframework.sqlhelper.core.rules.impl;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.rules.ToColumnCallbackComparisonOperator;
import pub.avalonframework.sqlhelper.core.rules.ToColumnComparisonOperator;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

/**
 * @author baichao
 */
public interface ToColumnCallbackComparisonOperatorImpl<T> extends ToColumnCallbackComparisonOperator<T>, ToColumnComparisonOperator<T> {

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return equalTo(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return notEqualTo(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return greaterThan(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return greaterThanAndEqualTo(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return lessThan(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return lessThanAndEqualTo(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        SC secondSc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        secondSc = secondColumnCallback.apply(secondSc);
        return between(sc, secondSc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return like(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return in(sc);
    }

    @Override
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnCallback.apply(sc);
        return notIn(sc);
    }
}