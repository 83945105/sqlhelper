package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * On比较条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface OnComparisonOperator<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    /**
     * 等于
     *
     * @param onBuilder On条件构建器
     * @return On条件模组
     */
    MO equalTo(OnBuilder onBuilder);

    /**
     * 不等于
     *
     * @param onBuilder On条件构建器
     * @return On条件模组
     */
    MO notEqualTo(OnBuilder onBuilder);

    /**
     * 大于
     *
     * @param onBuilder On条件构建器
     * @return On条件模组
     */
    MO greaterThan(OnBuilder onBuilder);

    /**
     * 大于等于
     *
     * @param onBuilder On条件构建器
     * @return On条件模组
     */
    MO greaterThanAndEqualTo(OnBuilder onBuilder);

    /**
     * 小于
     *
     * @param onBuilder On条件构建器
     * @return On条件模组
     */
    MO lessThan(OnBuilder onBuilder);

    /**
     * 小于等于
     *
     * @param onBuilder On条件构建器
     * @return On条件模组
     */
    MO lessThanAndEqualTo(OnBuilder onBuilder);

    /**
     * 等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO equalTo(Class<T> onClass,
                                                                     OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        return this.equalTo(onClass, null, onModelValue);
    }

    /**
     * 等于
     *
     * @param onClass      On关联模组类
     * @param alias        别名
     * @param onModelValue On处理
     * @return On条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO equalTo(Class<T> onClass,
                                                                     String alias,
                                                                     OnModelValue<T, TC, TO, TW, TS, TG> onModelValue);

    /**
     * 不等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO notEqualTo(Class<T> onClass,
                                                                        OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        return this.notEqualTo(onClass, null, onModelValue);
    }

    /**
     * 不等于
     *
     * @param onClass      On关联模组类
     * @param alias        别名
     * @param onModelValue On处理
     * @return On条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO notEqualTo(Class<T> onClass,
                                                                        String alias,
                                                                        OnModelValue<T, TC, TO, TW, TS, TG> onModelValue);

    /**
     * 大于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO greaterThan(Class<T> onClass,
                                                                         OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        return this.greaterThan(onClass, null, onModelValue);
    }

    /**
     * 大于
     *
     * @param onClass      On关联模组类
     * @param alias        别名
     * @param onModelValue On处理
     * @return On条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO greaterThan(Class<T> onClass,
                                                                         String alias,
                                                                         OnModelValue<T, TC, TO, TW, TS, TG> onModelValue);

    /**
     * 大于等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO greaterThanAndEqualTo(Class<T> onClass,
                                                                                   OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        return this.greaterThanAndEqualTo(onClass, null, onModelValue);
    }

    /**
     * 大于等于
     *
     * @param onClass      On关联模组类
     * @param alias        别名
     * @param onModelValue On处理
     * @return On条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO greaterThanAndEqualTo(Class<T> onClass,
                                                                                   String alias,
                                                                                   OnModelValue<T, TC, TO, TW, TS, TG> onModelValue);

    /**
     * 小于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO lessThan(Class<T> onClass,
                                                                      OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        return this.lessThan(onClass, null, onModelValue);
    }

    /**
     * 小于
     *
     * @param onClass      On关联模组类
     * @param alias        别名
     * @param onModelValue On处理
     * @return On条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO lessThan(Class<T> onClass,
                                                                      String alias,
                                                                      OnModelValue<T, TC, TO, TW, TS, TG> onModelValue);

    /**
     * 小于等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO lessThanAndEqualTo(Class<T> onClass,
                                                                                OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        return this.lessThanAndEqualTo(onClass, null, onModelValue);
    }

    /**
     * 小于等于
     *
     * @param onClass      On关联模组类
     * @param alias        别名
     * @param onModelValue On处理
     * @return On条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO lessThanAndEqualTo(Class<T> onClass,
                                                                                String alias,
                                                                                OnModelValue<T, TC, TO, TW, TS, TG> onModelValue);

}
