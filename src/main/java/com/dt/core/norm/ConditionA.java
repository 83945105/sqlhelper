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
public interface ConditionA<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    /**
     * 接收处理条件
     *
     * @param condition 连接条件
     * @param mainTable 主表的条件
     * @return 条件连接
     */
    WhereLink<M, ML, MO, MC, MS, MG> apply(WhereLink<M, ML, MO, MC, MS, MG> condition, MC mainTable);
}
