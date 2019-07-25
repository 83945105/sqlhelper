package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.WhereColumnCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Where条件比较运算符
 *
 * @author 白超
 * @date 2019/5/18
 */
public interface WhereComparisonOperator<T> extends ComparisonOperator<T> {

    /**
     * 等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T equalTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 不等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T notEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 大于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T greaterThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 大于等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 小于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T lessThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 小于等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 介于
     *
     * @param whereSqlPartDatumBuilder       {@link WhereSqlPartDatumBuilder}
     * @param secondWhereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T between(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder, WhereSqlPartDatumBuilder secondWhereSqlPartDatumBuilder);

    /**
     * 模糊匹配
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T like(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 在...内
     *
     * @param whereSqlPartDatumBuilders {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T in(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders);

    /**
     * 不在...内
     *
     * @param whereSqlPartDatumBuilders {@link WhereSqlPartDatumBuilder}
     * @return {@link Helper}
     */
    T notIn(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders);

    /**
     * 等于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T equalTo(ColumnHelper<?> columnHelper);

    /**
     * 等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 String tableAlias,
                                                 WhereColumnCallback<SC> callback);

    /**
     * 等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 WhereColumnCallback<SC> callback) {
        return equalTo(tableHelperClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T notEqualTo(ColumnHelper<?> columnHelper);

    /**
     * 不等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    String tableAlias,
                                                    WhereColumnCallback<SC> callback);

    /**
     * 不等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    WhereColumnCallback<SC> callback) {
        return notEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 大于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T greaterThan(ColumnHelper<?> columnHelper);

    /**
     * 大于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     String tableAlias,
                                                     WhereColumnCallback<SC> callback);

    /**
     * 大于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     WhereColumnCallback<SC> callback) {
        return greaterThan(tableHelperClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T greaterThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * 大于等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               String tableAlias,
                                                               WhereColumnCallback<SC> callback);

    /**
     * 大于等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               WhereColumnCallback<SC> callback) {
        return greaterThanAndEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 小于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T lessThan(ColumnHelper<?> columnHelper);

    /**
     * 小于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  String tableAlias,
                                                  WhereColumnCallback<SC> callback);

    /**
     * 小于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  WhereColumnCallback<SC> callback) {
        return lessThan(tableHelperClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T lessThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * 小于等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            String tableAlias,
                                                            WhereColumnCallback<SC> callback);

    /**
     * 小于等于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            WhereColumnCallback<SC> callback) {
        return lessThanAndEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 介于
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T between(ColumnHelper<?> columnHelper);

    /**
     * 介于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass,
                                                 String tableAlias,
                                                 WhereColumnCallback<SC> callback);

    /**
     * 介于
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass,
                                                 WhereColumnCallback<SC> callback) {
        return between(tableHelperClass, null, callback);
    }

    /**
     * 模糊匹配
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T like(ColumnHelper<?> columnHelper);

    /**
     * 模糊匹配
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass,
                                              String tableAlias,
                                              WhereColumnCallback<SC> callback);

    /**
     * 模糊匹配
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass,
                                              WhereColumnCallback<SC> callback) {
        return like(tableHelperClass, null, callback);
    }

    /**
     * 在...内
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T in(ColumnHelper<?> columnHelper);

    /**
     * 在...内
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass,
                                            String tableAlias,
                                            WhereColumnCallback<SC> callback);

    /**
     * 在...内
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass,
                                            WhereColumnCallback<SC> callback) {
        return in(tableHelperClass, null, callback);
    }

    /**
     * 不在...内
     *
     * @param columnHelper 列助手
     * @return {@link Helper}
     */
    T notIn(ColumnHelper<?> columnHelper);

    /**
     * 不在...内
     *
     * @param tableHelperClass {@link TableHelper}
     * @param tableAlias       表别名
     * @param callback         列回调
     * @return {@link Helper}
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass,
                                               String tableAlias,
                                               WhereColumnCallback<SC> callback);

    /**
     * 不在...内
     *
     * @param tableHelperClass {@link TableHelper}
     * @param callback         列回调
     * @return {@link Helper}
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass,
                                               WhereColumnCallback<SC> callback) {
        return notIn(tableHelperClass, null, callback);
    }

    /*==================================================================SubQuery=========================================================================*/

    /**
     * 等于
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T equalToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 不等于
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T notEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 大于
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T greaterThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 大于等于
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 小于
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T lessThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 小于等于
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 模糊匹配
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T likeSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 在...内
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T inSubQuery(SubQueryCallback subQueryCallback);

    /**
     * 不在...内
     *
     * @param subQueryCallback 列回调
     * @return
     */
    T notInSubQuery(SubQueryCallback subQueryCallback);

}
