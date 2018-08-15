package com.dt.core.bean;

import com.dt.core.norm.Model;

/**
 * 排序模组
 * 用于记录排序信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SortModel<T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    protected SortBuilder<T, TL, TO, TC, TS, TG> sortBuilder = new SortBuilder<>((TS) this);

    public SortBuilder<T, TL, TO, TC, TS, TG> getSortBuilder() {
        return this.sortBuilder;
    }
}
