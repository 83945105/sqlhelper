package com.dt.core.data;

import com.dt.core.bean.*;
import com.dt.core.norm.Model;

/**
 * @author 白超
 * @date 2018/8/20
 */
public final class FinalSqlData<M extends Model> extends AbstractSqlData<M> {

    public FinalSqlData(DataBaseType dataBaseType) {
        super(dataBaseType);
    }
}
