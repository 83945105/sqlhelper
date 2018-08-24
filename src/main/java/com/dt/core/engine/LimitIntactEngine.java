package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.build.SqlBuilder;
import com.dt.core.data.SqlData;
import com.dt.core.norm.Model;
import com.dt.core.sql.Query;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class LimitIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends SqlEngine<M> implements Query<SqlBuilder> {

    LimitIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    LimitIntactEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    public LimitIntactEngine limit(int start, Integer end) {
        this.sqlData.setLimitStart(start);
        this.sqlData.setLimitEnd(end);
        return this;
    }

    public SqlData<M> getData() {
        return this.sqlData;
    }

    @Override
    public SqlBuilder query() {
        return this.sqlBuilderProxy.query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilderProxy.queryCount();
    }

}
