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
     * @return Where条件模组
     */
    T equalTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 不等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return Where条件模组
     */
    T notEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 大于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return Where条件模组
     */
    T greaterThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 大于等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return Where条件模组
     */
    T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 小于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return Where条件模组
     */
    T lessThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 小于等于
     *
     * @param whereSqlPartDatumBuilder {@link WhereSqlPartDatumBuilder}
     * @return Where条件模组
     */
    T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder);

    /**
     * 等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 String tableAlias,
                                                 WhereColumnCallback<SC> callback);

    /**
     * 等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 WhereColumnCallback<SC> callback) {
        return equalTo(tableHelperClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    String tableAlias,
                                                    WhereColumnCallback<SC> callback);

    /**
     * 不等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    WhereColumnCallback<SC> callback) {
        return notEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 大于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     String tableAlias,
                                                     WhereColumnCallback<SC> callback);

    /**
     * 大于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     WhereColumnCallback<SC> callback) {
        return greaterThan(tableHelperClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               String tableAlias,
                                                               WhereColumnCallback<SC> callback);

    /**
     * 大于等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               WhereColumnCallback<SC> callback) {
        return greaterThanAndEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 小于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  String tableAlias,
                                                  WhereColumnCallback<SC> callback);

    /**
     * 小于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  WhereColumnCallback<SC> callback) {
        return lessThan(tableHelperClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            String tableAlias,
                                                            WhereColumnCallback<SC> callback);

    /**
     * 小于等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            WhereColumnCallback<SC> callback) {
        return lessThanAndEqualTo(tableHelperClass, null, callback);
    }

    /*==================================================================SubQuery=========================================================================*/

    /**
     * 等于
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.equalToSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.equalToSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 不等于
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notEqualToSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notEqualToSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 大于
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 大于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 大于等于
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanAndEqualToSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanAndEqualToSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 小于
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 小于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 小于等于
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于等于
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanAndEqualToSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanAndEqualToSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 模糊匹配
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 模糊匹配
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.likeSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 模糊匹配
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.likeSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 在...内
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 在...内
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.inSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 在...内
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.inSubQuery(null, tableHelperClass, tableAlias, callback);
    }

    /**
     * 不在...内
     *
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不在...内
     *
     * @param tableHelperClass
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notInSubQuery(null, tableHelperClass, null, callback);
    }

    /**
     * 不在...内
     *
     * @param tableHelperClass
     * @param tableAlias
     * @param callback
     * @return
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notInSubQuery(null, tableHelperClass, tableAlias, callback);
    }

}
