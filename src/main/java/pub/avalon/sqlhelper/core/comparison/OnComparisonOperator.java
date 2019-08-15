package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.builder.JoinSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.callback.OnColumnCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface OnComparisonOperator<T> extends ComparisonOperator<T> {

    /**
     * equal to
     *
     * @param joinSqlPartDatumBuilder {@link JoinSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T equalTo(JoinSqlPartDatumBuilder joinSqlPartDatumBuilder);

    /**
     * not equal to
     *
     * @param joinSqlPartDatumBuilder {@link JoinSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notEqualTo(JoinSqlPartDatumBuilder joinSqlPartDatumBuilder);

    /**
     * greater than
     *
     * @param joinSqlPartDatumBuilder {@link JoinSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThan(JoinSqlPartDatumBuilder joinSqlPartDatumBuilder);

    /**
     * greater than or equal to
     *
     * @param joinSqlPartDatumBuilder {@link JoinSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(JoinSqlPartDatumBuilder joinSqlPartDatumBuilder);

    /**
     * less than
     *
     * @param joinSqlPartDatumBuilder {@link JoinSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThan(JoinSqlPartDatumBuilder joinSqlPartDatumBuilder);

    /**
     * less than or equal to
     *
     * @param joinSqlPartDatumBuilder {@link JoinSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(JoinSqlPartDatumBuilder joinSqlPartDatumBuilder);

    /**
     * equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> onColumnCallback);

    /**
     * equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, OnColumnCallback<SC> onColumnCallback) {
        return this.equalTo(tableHelperClass, null, onColumnCallback);
    }

    /**
     * not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> onColumnCallback);

    /**
     * not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, OnColumnCallback<SC> onColumnCallback) {
        return this.notEqualTo(tableHelperClass, null, onColumnCallback);
    }

    /**
     * greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> onColumnCallback);

    /**
     * greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, OnColumnCallback<SC> onColumnCallback) {
        return this.greaterThan(tableHelperClass, null, onColumnCallback);
    }

    /**
     * greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> onColumnCallback);

    /**
     * greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, OnColumnCallback<SC> onColumnCallback) {
        return this.greaterThanAndEqualTo(tableHelperClass, null, onColumnCallback);
    }

    /**
     * less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> onColumnCallback);

    /**
     * less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, OnColumnCallback<SC> onColumnCallback) {
        return this.lessThan(tableHelperClass, null, onColumnCallback);
    }

    /**
     * less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> onColumnCallback);

    /**
     * less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onColumnCallback {@link OnColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, OnColumnCallback<SC> onColumnCallback) {
        return this.lessThanAndEqualTo(tableHelperClass, null, onColumnCallback);
    }
}