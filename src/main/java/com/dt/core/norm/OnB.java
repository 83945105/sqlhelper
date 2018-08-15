package com.dt.core.norm;

import com.dt.core.bean.*;

/**
 * 连接查询On条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface OnB<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    OnBuilder<M, ML, MO, MC, MS, MG> apply(MO table);

}
