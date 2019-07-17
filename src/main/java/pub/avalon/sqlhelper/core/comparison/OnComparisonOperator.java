package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.callback.OnColumnCallback;
import pub.avalon.sqlhelper.core.helper.*;

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
     * @param onSqlPartDatumBuilder {@link OnSqlPartDatumBuilder}
     * @return On条件模组
     */
    T equalTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder);

    /**
     * 不等于
     *
     * @param onSqlPartDatumBuilder {@link OnSqlPartDatumBuilder}
     * @return On条件模组
     */
    T notEqualTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder);

    /**
     * 大于
     *
     * @param onSqlPartDatumBuilder {@link OnSqlPartDatumBuilder}
     * @return On条件模组
     */
    T greaterThan(OnSqlPartDatumBuilder onSqlPartDatumBuilder);

    /**
     * 大于等于
     *
     * @param onSqlPartDatumBuilder {@link OnSqlPartDatumBuilder}
     * @return On条件模组
     */
    T greaterThanAndEqualTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder);

    /**
     * 小于
     *
     * @param onSqlPartDatumBuilder {@link OnSqlPartDatumBuilder}
     * @return On条件模组
     */
    T lessThan(OnSqlPartDatumBuilder onSqlPartDatumBuilder);

    /**
     * 小于等于
     *
     * @param onSqlPartDatumBuilder {@link OnSqlPartDatumBuilder}
     * @return On条件模组
     */
    T lessThanAndEqualTo(OnSqlPartDatumBuilder onSqlPartDatumBuilder);

    /**
     * 等于
     *
     * @param tableHelperClass On关联模组类
     * @param tableAlias       表别名
     * @param callback         On处理
     * @return On条件模组
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 String tableAlias,
                                                 OnColumnCallback<SC> callback);

    /**
     * 等于
     *
     * @param tableHelperClass On关联模组类
     * @param callback         On处理
     * @return On条件模组
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass,
                                                 OnColumnCallback<SC> callback) {
        return this.equalTo(tableHelperClass, null, callback);
    }

    /**
     * 不等于
     *
     * @param tableHelperClass On关联模组类
     * @param tableAlias       表别名
     * @param callback         On处理
     * @return On条件模组
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    String tableAlias,
                                                    OnColumnCallback<SC> callback);

    /**
     * 不等于
     *
     * @param tableHelperClass On关联模组类
     * @param callback         On处理
     * @return On条件模组
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass,
                                                    OnColumnCallback<SC> callback) {
        return this.notEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 大于
     *
     * @param tableHelperClass On关联模组类
     * @param tableAlias       表别名
     * @param callback         On处理
     * @return On条件模组
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     String tableAlias,
                                                     OnColumnCallback<SC> callback);

    /**
     * 大于
     *
     * @param tableHelperClass On关联模组类
     * @param callback         On处理
     * @return On条件模组
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass,
                                                     OnColumnCallback<SC> callback) {
        return this.greaterThan(tableHelperClass, null, callback);
    }

    /**
     * 大于等于
     *
     * @param tableHelperClass On关联模组类
     * @param tableAlias       表别名
     * @param callback         On处理
     * @return On条件模组
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               String tableAlias,
                                                               OnColumnCallback<SC> callback);

    /**
     * 大于等于
     *
     * @param tableHelperClass On关联模组类
     * @param callback         On处理
     * @return On条件模组
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass,
                                                               OnColumnCallback<SC> callback) {
        return this.greaterThanAndEqualTo(tableHelperClass, null, callback);
    }

    /**
     * 小于
     *
     * @param tableHelperClass On关联模组类
     * @param tableAlias       表别名
     * @param callback         On处理
     * @return On条件模组
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  String tableAlias,
                                                  OnColumnCallback<SC> callback);

    /**
     * 小于
     *
     * @param tableHelperClass On关联模组类
     * @param callback         On处理
     * @return On条件模组
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass,
                                                  OnColumnCallback<SC> callback) {
        return this.lessThan(tableHelperClass, null, callback);
    }

    /**
     * 小于等于
     *
     * @param tableHelperClass On关联模组类
     * @param tableAlias       表别名
     * @param callback         On处理
     * @return On条件模组
     */
    <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            String tableAlias,
                                                            OnColumnCallback<SC> callback);

    /**
     * 小于等于
     *
     * @param tableHelperClass On关联模组类
     * @param callback         On处理
     * @return On条件模组
     */
    default <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass,
                                                            OnColumnCallback<SC> callback) {
        return this.lessThanAndEqualTo(tableHelperClass, null, callback);
    }

}
