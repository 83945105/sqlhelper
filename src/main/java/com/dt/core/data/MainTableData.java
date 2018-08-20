package com.dt.core.data;

import com.dt.core.norm.Model;

/**
 * 主表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class MainTableData<T extends Model> extends AbstractTableData<T> {

    public MainTableData(Class<T> tableClass) {
        super(tableClass);
    }

}
