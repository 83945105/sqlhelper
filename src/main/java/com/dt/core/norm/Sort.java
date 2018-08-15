package com.dt.core.norm;

import com.dt.core.bean.*;

/**
 * 排序处理
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface Sort<T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    TS apply(TS table);
}
