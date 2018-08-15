package com.dt.core.norm;

import com.dt.core.bean.*;

/**
 * 条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface ConditionB<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>,
        T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    /**
     * 接收处理条件
     *
     * @param condition 连接条件
     * @param table     指定表条件
     * @param mainTable 主表的条件
     * @return 条件连接
     */
    WhereLink<M, ML, MO, MC, MS, MG> apply(WhereLink<M, ML, MO, MC, MS, MG> condition, TC table, MC mainTable);
}
