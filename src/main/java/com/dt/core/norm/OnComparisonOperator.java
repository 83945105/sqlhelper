package com.dt.core.norm;

import com.dt.core.bean.*;

/**
 * On比较条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface OnComparisonOperator<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    MO equalTo(OnBuilder onBuilder);

    MO notEqualTo(OnBuilder onBuilder);

    MO greaterThan(OnBuilder onBuilder);

    MO greaterThanAndEqualTo(OnBuilder onBuilder);

    MO lessThan(OnBuilder onBuilder);

    MO lessThanAndEqualTo(OnBuilder onBuilder);

    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO equalTo(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO notEqualTo(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThan(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThanAndEqualTo(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThan(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThanAndEqualTo(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on);

}
