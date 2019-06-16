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
     * @param callback 条件构建器
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 String alias,
                                                 WhereColumnCallback<SC> callback);

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
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    String alias,
                                                    WhereColumnCallback<SC> callback);

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
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     String alias,
                                                     WhereColumnCallback<SC> callback);

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
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               String alias,
                                                               WhereColumnCallback<SC> callback);

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
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  String alias,
                                                  WhereColumnCallback<SC> callback);

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
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            String alias,
                                                            WhereColumnCallback<SC> callback);

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
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.equalToSubQuery(null, modelClass, null, callback);
    }

    /**
     * 等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.equalToSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 不等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notEqualToSubQuery(null, modelClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notEqualToSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 大于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanSubQuery(null, modelClass, null, callback);
    }

    /**
     * 大于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 大于等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanAndEqualToSubQuery(null, modelClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanAndEqualToSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 小于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanSubQuery(null, modelClass, null, callback);
    }

    /**
     * 小于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 小于等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanAndEqualToSubQuery(null, modelClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanAndEqualToSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 模糊匹配
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 模糊匹配
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.likeSubQuery(null, modelClass, null, callback);
    }

    /**
     * 模糊匹配
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.likeSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 在...内
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 在...内
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.inSubQuery(null, modelClass, null, callback);
    }

    /**
     * 在...内
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.inSubQuery(null, modelClass, alias, callback);
    }

    /**
     * 不在...内
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不在...内
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notInSubQuery(null, modelClass, null, callback);
    }

    /**
     * 不在...内
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notInSubQuery(null, modelClass, alias, callback);
    }

}
