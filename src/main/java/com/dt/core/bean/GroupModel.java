package com.dt.core.bean;

import com.dt.core.norm.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分组模组
 * <p>用户记录分组相关信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupModel<T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    /**
     * 存储列名
     */
    private List<String> columns = new ArrayList<>();

    /**
     * 获取列名集合
     *
     * @return ArrayList {@link ArrayList}
     */
    public List<String> getColumns() {
        return this.columns;
    }

    /**
     * 新增列名
     *
     * @param column 列名
     */
    protected void addColumn(String column) {
        this.columns.add(column);
    }
}
