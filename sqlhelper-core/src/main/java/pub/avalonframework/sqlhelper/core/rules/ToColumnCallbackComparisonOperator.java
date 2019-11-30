package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface ToColumnCallbackComparisonOperator<T> {

    /**
     * equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return equalTo(tableHelperClass, null, columnCallback);
    }

    /**
     * not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return notEqualTo(tableHelperClass, null, columnCallback);
    }

    /**
     * greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return greaterThan(tableHelperClass, null, columnCallback);
    }

    /**
     * greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return greaterThanAndEqualTo(tableHelperClass, null, columnCallback);
    }

    /**
     * less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lessThan(tableHelperClass, null, columnCallback);
    }

    /**
     * less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lessThanAndEqualTo(tableHelperClass, null, columnCallback);
    }

    /**
     * between ... and ...
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param tableAlias           table alias
     * @param columnCallback       {@link ColumnCallback}
     * @param secondColumnCallback {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback);

    /**
     * between ... and ...
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param columnCallback       {@link ColumnCallback}
     * @param secondColumnCallback {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback) {
        return between(tableHelperClass, null, columnCallback, secondColumnCallback);
    }

    /**
     * like
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * like
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return like(tableHelperClass, null, columnCallback);
    }

    /**
     * in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return in(tableHelperClass, null, columnCallback);
    }

    /**
     * not in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * not in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return notIn(tableHelperClass, null, columnCallback);
    }
}