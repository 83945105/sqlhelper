package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.FinalSqlData;
import com.dt.core.data.MainTableData;
import com.dt.core.data.SqlData;
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
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends SqlEngine<M> {

    protected FinalSqlData<M> sqlData;

    LimitEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    LimitEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    public SqlEngine limit(int start, Integer end) {
        this.sqlData.setLimitStart(start);
        this.sqlData.setLimitEnd(end);
        return this;
    }

    public SqlEngine limit(int start) {
        return limit(start, null);
    }

    public SqlData<M> getData() {
        return this.sqlData;
    }

/*    @Override
    public Model getTableModel() {
        return this.sqlData.getMainTableData().getTableModel();
    }

    @Override
    public Class getTableClass() {
        return this.sqlData.getMainTableData().getTableClass();
    }

    @Override
    public String getTableName() {
        return this.sqlData.getMainTableData().getTableName();
    }

    @Override
    public String getTableAlias() {
        return this.sqlData.getMainTableData().getTableAlias();
    }

    @Override
    public String getPrimaryKeyName() {
        return this.sqlData.getMainTableData().getPrimaryKeyName();
    }

    @Override
    public String getPrimaryKeyAlias() {
        return this.sqlData.getMainTableData().getPrimaryKeyAlias();
    }*/

}
