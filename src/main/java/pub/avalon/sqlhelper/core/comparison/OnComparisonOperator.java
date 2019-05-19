package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.callback.OnColumnCallback;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * On比较条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface OnComparisonOperator<T> extends ComparisonOperator<T> {

    /**
     * 等于
     *
     * @param onSqlDataBuilder On条件构建器
     * @return On条件模组
     */
    T equalTo(OnSqlDataBuilder onSqlDataBuilder);

    /**
     * 不等于
     *
     * @param onSqlDataBuilder On条件构建器
     * @return On条件模组
     */
    T notEqualTo(OnSqlDataBuilder onSqlDataBuilder);

    /**
     * 大于
     *
     * @param onSqlDataBuilder On条件构建器
     * @return On条件模组
     */
    T greaterThan(OnSqlDataBuilder onSqlDataBuilder);

    /**
     * 大于等于
     *
     * @param onSqlDataBuilder On条件构建器
     * @return On条件模组
     */
    T greaterThanAndEqualTo(OnSqlDataBuilder onSqlDataBuilder);

    /**
     * 小于
     *
     * @param onSqlDataBuilder On条件构建器
     * @return On条件模组
     */
    T lessThan(OnSqlDataBuilder onSqlDataBuilder);

    /**
     * 小于等于
     *
     * @param onSqlDataBuilder On条件构建器
     * @return On条件模组
     */
    T lessThanAndEqualTo(OnSqlDataBuilder onSqlDataBuilder);

    /**
     * 等于
     *
     * @param tableModelClass On关联模组类
     * @param alias           别名
     * @param callback    On处理
     * @return On条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalTo(Class<S> tableModelClass,
                                                   String alias,
                                                   OnColumnCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 等于
     *
     * @param tableModelClass On关联模组类
     * @param callback    On处理
     * @return On条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalTo(Class<S> tableModelClass,
                                                   OnColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.equalTo(tableModelClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param tableModelClass On关联模组类
     * @param alias           别名
     * @param callback    On处理
     * @return On条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualTo(Class<S> tableModelClass,
                                                      String alias,
                                                      OnColumnCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 不等于
     *
     * @param tableModelClass On关联模组类
     * @param callback    On处理
     * @return On条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualTo(Class<S> tableModelClass,
                                                      OnColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.notEqualTo(tableModelClass, null, callback);
    }

    /**
     * 大于
     *
     * @param tableModelClass On关联模组类
     * @param alias           别名
     * @param callback    On处理
     * @return On条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThan(Class<S> tableModelClass,
                                                       String alias,
                                                       OnColumnCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于
     *
     * @param tableModelClass On关联模组类
     * @param callback    On处理
     * @return On条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThan(Class<S> tableModelClass,
                                                       OnColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThan(tableModelClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param tableModelClass On关联模组类
     * @param alias           别名
     * @param callback    On处理
     * @return On条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualTo(Class<S> tableModelClass,
                                                                 String alias,
                                                                 OnColumnCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 大于等于
     *
     * @param tableModelClass On关联模组类
     * @param callback    On处理
     * @return On条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualTo(Class<S> tableModelClass,
                                                                 OnColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.greaterThanAndEqualTo(tableModelClass, null, callback);
    }

    /**
     * 小于
     *
     * @param tableModelClass On关联模组类
     * @param alias           别名
     * @param callback    On处理
     * @return On条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThan(Class<S> tableModelClass,
                                                    String alias,
                                                    OnColumnCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于
     *
     * @param tableModelClass On关联模组类
     * @param callback    On处理
     * @return On条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThan(Class<S> tableModelClass,
                                                    OnColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThan(tableModelClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param tableModelClass On关联模组类
     * @param alias           别名
     * @param callback    On处理
     * @return On条件模组
     */
    <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualTo(Class<S> tableModelClass,
                                                              String alias,
                                                              OnColumnCallback<S, SO, SC, SW, SG, SS> callback);

    /**
     * 小于等于
     *
     * @param tableModelClass On关联模组类
     * @param callback    On处理
     * @return On条件模组
     */
    default <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualTo(Class<S> tableModelClass,
                                                              OnColumnCallback<S, SO, SC, SW, SG, SS> callback) {
        return this.lessThanAndEqualTo(tableModelClass, null, callback);
    }

}
