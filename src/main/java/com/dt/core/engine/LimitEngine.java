package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.EngineData;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.Data;
import com.dt.core.norm.Engine;
import com.dt.core.norm.Model;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class LimitEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> implements Engine {

    protected Data<M, ML, MO, MC, MS, MG> data;

    @SuppressWarnings("unchecked")
    LimitEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        MainTableData data = new MainTableData(mainClass);
        this.data = new EngineData<>(dataBaseType);
        this.data.setMainTableData(data);
    }

    @SuppressWarnings("unchecked")
    LimitEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        MainTableData data = new MainTableData(mainClass);
        data.setTableName(tableName);
        this.data = new EngineData<>(dataBaseType);
        this.data.setMainTableData(data);
    }

    public Engine limit(int start, Integer end) {
        this.data.setLimitStart(start);
        this.data.setLimitEnd(end);
        return this;
    }

    public Engine limit(int start) {
        return limit(start, null);
    }

    public Data<M, ML, MO, MC, MS, MG> getData() {
        return this.data;
    }

    @Override
    public Model getTableModel() {
        return this.data.getMainTableData().getTableModel();
    }

    @Override
    public Class getTableClass() {
        return this.data.getMainTableData().getTableClass();
    }

    @Override
    public String getTableName() {
        return this.data.getMainTableData().getTableName();
    }

    @Override
    public String getTableAlias() {
        return this.data.getMainTableData().getTableAlias();
    }

    @Override
    public String getPrimaryKeyName() {
        return this.data.getMainTableData().getPrimaryKeyName();
    }

    @Override
    public String getPrimaryKeyAlias() {
        return this.data.getMainTableData().getPrimaryKeyAlias();
    }

}
