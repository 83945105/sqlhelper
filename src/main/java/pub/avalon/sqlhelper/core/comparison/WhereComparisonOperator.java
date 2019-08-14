package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.WhereColumnCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface WhereComparisonOperator<T> extends ComparisonOperator<T> {

    /**
     * equal to
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T equalTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * not equal to
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * greater than
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * greater than or equal to
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * less than
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * less than or equal to
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * between ... and ...
     *
     * @param whereSqlPartDatumBuilder       {@link WhereSqlPartDatumBuilder}
     * @param secondWhereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T between(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder, WhereSqlPartDatumBuilder secondWhereSqlPartDatumBuilder);

    /**
     * like
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T like(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * in
     *
     * @param whereSqlPartDatumBuilders {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T in(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders);

    /**
     * not in
     *
     * @param whereSqlPartDatumBuilders {@link WhereSqlPartDatumBuilder}
     * @return extends {@link Helper} object
     */
    T notIn(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders);

    /**
     * equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T equalTo(ColumnHelper<?> columnHelper);

    /**
     * equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return equalTo(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * not equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T notEqualTo(ColumnHelper<?> columnHelper);

    /**
     * not equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * not equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return notEqualTo(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * greater than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T greaterThan(ColumnHelper<?> columnHelper);

    /**
     * greater than
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * greater than
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return greaterThan(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * greater than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * greater than or equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * greater than or equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return greaterThanAndEqualTo(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * less than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lessThan(ColumnHelper<?> columnHelper);

    /**
     * less than
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * less than
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return lessThan(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * less than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * less than or equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * less than or equal to
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return lessThanAndEqualTo(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * between ... and ...
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T between(ColumnHelper<?> columnHelper);

    /**
     * between ... and ...
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * between ... and ...
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return between(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * like
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T like(ColumnHelper<?> columnHelper);

    /**
     * like
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * like
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return like(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * in
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T in(ColumnHelper<?> columnHelper);

    /**
     * in
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * in
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return in(tableHelperClass, null, whereColumnCallback);
    }

    /**
     * not in
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T notIn(ColumnHelper<?> columnHelper);

    /**
     * not in
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param tableAlias          table alias
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> whereColumnCallback);

    /**
     * not in
     *
     * @param tableHelperClass    extends {@link TableHelper} class
     * @param whereColumnCallback {@link WhereColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, WhereColumnCallback<SC> whereColumnCallback) {
        return notIn(tableHelperClass, null, whereColumnCallback);
    }

    /*==================================================================SubQuery=========================================================================*/

    /**
     * 等于
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T equalToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 不等于
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T notEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 大于
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T greaterThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 大于等于
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 小于
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T lessThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 小于等于
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 模糊匹配
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T likeSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 在...内
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T inSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 不在...内
     *
     * @param subQueryCallback {@link WhereColumnCallback}
     * @return
     */
    T notInSubQuery(SubQueryCallback subQueryCallback);

}
