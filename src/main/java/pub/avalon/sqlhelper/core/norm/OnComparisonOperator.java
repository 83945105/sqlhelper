package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * On比较条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface OnComparisonOperator<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

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
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO equalTo(Class<T> onClass,
                                                                     OnModelValue<T, TL, TO, TC, TS, TG> onModelValue) {
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
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO equalTo(Class<T> onClass,
                                                                     String alias,
                                                                     OnModelValue<T, TL, TO, TC, TS, TG> onModelValue);

    /**
     * 不等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO notEqualTo(Class<T> onClass,
                                                                        OnModelValue<T, TL, TO, TC, TS, TG> onModelValue) {
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
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO notEqualTo(Class<T> onClass,
                                                                        String alias,
                                                                        OnModelValue<T, TL, TO, TC, TS, TG> onModelValue);

    /**
     * 大于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThan(Class<T> onClass,
                                                                         OnModelValue<T, TL, TO, TC, TS, TG> onModelValue) {
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
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThan(Class<T> onClass,
                                                                         String alias,
                                                                         OnModelValue<T, TL, TO, TC, TS, TG> onModelValue);

    /**
     * 大于等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThanAndEqualTo(Class<T> onClass,
                                                                                   OnModelValue<T, TL, TO, TC, TS, TG> onModelValue) {
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
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThanAndEqualTo(Class<T> onClass,
                                                                                   String alias,
                                                                                   OnModelValue<T, TL, TO, TC, TS, TG> onModelValue);

    /**
     * 小于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThan(Class<T> onClass,
                                                                      OnModelValue<T, TL, TO, TC, TS, TG> onModelValue) {
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
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThan(Class<T> onClass,
                                                                      String alias,
                                                                      OnModelValue<T, TL, TO, TC, TS, TG> onModelValue);

    /**
     * 小于等于
     *
     * @param onClass      On关联模组类
     * @param onModelValue On处理
     * @return On条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThanAndEqualTo(Class<T> onClass,
                                                                                OnModelValue<T, TL, TO, TC, TS, TG> onModelValue) {
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
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThanAndEqualTo(Class<T> onClass,
                                                                                String alias,
                                                                                OnModelValue<T, TL, TO, TC, TS, TG> onModelValue);

}
