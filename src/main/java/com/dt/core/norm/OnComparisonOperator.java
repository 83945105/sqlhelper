package com.dt.core.norm;

import com.dt.core.bean.*;

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
     * @param alias   别名
     * @param onClass On关联模组类
     * @param on      On处理
     * @return On条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO equalTo(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

    /**
     * 不等于
     *
     * @param alias   别名
     * @param onClass On关联模组类
     * @param on      On处理
     * @return On条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO notEqualTo(String alias,
                                                                        Class<T> onClass,
                                                                        OnB<T, TL, TO, TC, TS, TG> on);

    /**
     * 大于
     *
     * @param alias   别名
     * @param onClass On关联模组类
     * @param on      On处理
     * @return On条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThan(String alias,
                                                                         Class<T> onClass,
                                                                         OnB<T, TL, TO, TC, TS, TG> on);

    /**
     * 大于等于
     *
     * @param alias   别名
     * @param onClass On关联模组类
     * @param on      On处理
     * @return On条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThanAndEqualTo(String alias,
                                                                                   Class<T> onClass,
                                                                                   OnB<T, TL, TO, TC, TS, TG> on);

    /**
     * 小于
     *
     * @param alias   别名
     * @param onClass On关联模组类
     * @param on      On处理
     * @return On条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThan(String alias,
                                                                      Class<T> onClass,
                                                                      OnB<T, TL, TO, TC, TS, TG> on);

    /**
     * 小于等于
     *
     * @param alias   别名
     * @param onClass On关联模组类
     * @param on      On处理
     * @return On条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThanAndEqualTo(String alias,
                                                                                Class<T> onClass,
                                                                                OnB<T, TL, TO, TC, TS, TG> on);

}
