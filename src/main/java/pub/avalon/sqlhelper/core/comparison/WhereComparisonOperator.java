package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.WhereColumnCallback;
import pub.avalon.sqlhelper.core.modelbuilder.*;

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
     * @param whereSqlDataBuilder Where条件构建器
     * @return Where条件模组
     */
    T equalTo(WhereSqlDataBuilder whereSqlDataBuilder);

    /**
     * 不等于
     *
     * @param whereSqlDataBuilder Where条件构建器
     * @return Where条件模组
     */
    T notEqualTo(WhereSqlDataBuilder whereSqlDataBuilder);

    /**
     * 大于
     *
     * @param whereSqlDataBuilder Where条件构建器
     * @return Where条件模组
     */
    T greaterThan(WhereSqlDataBuilder whereSqlDataBuilder);

    /**
     * 大于等于
     *
     * @param whereSqlDataBuilder Where条件构建器
     * @return Where条件模组
     */
    T greaterThanAndEqualTo(WhereSqlDataBuilder whereSqlDataBuilder);

    /**
     * 小于
     *
     * @param whereSqlDataBuilder Where条件构建器
     * @return Where条件模组
     */
    T lessThan(WhereSqlDataBuilder whereSqlDataBuilder);

    /**
     * 小于等于
     *
     * @param whereSqlDataBuilder Where条件构建器
     * @return Where条件模组
     */
    T lessThanAndEqualTo(WhereSqlDataBuilder whereSqlDataBuilder);

    /**
     * 等于
     *
     * @param callback 条件构建器
     * @return Where条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalTo(Class<S> tableModelClass,
                                                   String alias,
                                                   WhereColumnCallback<S, SO, SC, SW, SG, SS> callback);

    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalTo(Class<S> tableModelClass,
                                                   WhereColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return equalTo(tableModelClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualTo(Class<S> tableModelClass,
                                                      String alias,
                                                      WhereColumnCallback<S, SO, SC, SW, SG, SS> callback);

    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualTo(Class<S> tableModelClass,
                                                      WhereColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return notEqualTo(tableModelClass, null, callback);
    }

    /**
     * 大于
     *
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThan(Class<S> tableModelClass,
                                                       String alias,
                                                       WhereColumnCallback<S, SO, SC, SW, SG, SS> callback);

    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThan(Class<S> tableModelClass,
                                                       WhereColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return greaterThan(tableModelClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualTo(Class<S> tableModelClass,
                                                                 String alias,
                                                                 WhereColumnCallback<S, SO, SC, SW, SG, SS> callback);

    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualTo(Class<S> tableModelClass,
                                                                 WhereColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return greaterThanAndEqualTo(tableModelClass, null, callback);
    }

    /**
     * 小于
     *
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThan(Class<S> tableModelClass,
                                                    String alias,
                                                    WhereColumnCallback<S, SO, SC, SW, SG, SS> callback);

    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThan(Class<S> tableModelClass,
                                                    WhereColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return lessThan(tableModelClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param callback Where条件构建器
     * @return Where条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualTo(Class<S> tableModelClass,
                                                              String alias,
                                                              WhereColumnCallback<S, SO, SC, SW, SG, SS> callback);

    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualTo(Class<S> tableModelClass,
                                                              WhereColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return lessThanAndEqualTo(tableModelClass, null, callback);
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于等于
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualToSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualToSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T likeSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 模糊匹配
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T likeSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T likeSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T inSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 在...内
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T inSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T inSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notInSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不在...内
     *
     * @param modelClass Model类
     * @param callback   子查询
     * @return Where条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notInSubQuery(Class<S> modelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notInSubQuery(Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notInSubQuery(null, modelClass, alias, callback);
    }

}
