package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.MainTableData;
import com.dt.core.data.VirtualFieldData;
import com.dt.core.norm.Column;
import com.dt.core.norm.Model;
import com.dt.core.parser.ColumnParser;

import java.util.Map;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends WhereEngine<M, ML, MO, MC, MS, MG> {

    private ColumnParser columnParser = ColumnParser.getInstance();

    public ColumnEngine(Class<M> mainClass) {
        super(mainClass);
    }

    ColumnEngine(Class<M> mainClass, String tableName) {
        super(mainClass, tableName);
    }

    @SuppressWarnings("unchecked")
    public ColumnEngine<M, ML, MO, MC, MS, MG> column(Column<M, ML, MO, MC, MS, MG> column) {
        MainTableData tableData = this.data.getMainTableData();
        Map<String, String> columns = column.apply((ML) tableData.getTable().getColumn()).getColumnAliasMap();
        if(columns.size() == 0) {
            columns = tableData.getTable().getColumnAliasMap();
        }
        tableData.addColumnAliasMap(columns);
        this.data.addColumnData(tableData);
        return this;
    }

    public ColumnEngine<M, ML, MO, MC, MS, MG> virtualColumn(String value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.data.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnEngine<M, ML, MO, MC, MS, MG> virtualColumn(int value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.data.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnEngine<M, ML, MO, MC, MS, MG> virtualColumn(long value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.data.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnEngine<M, ML, MO, MC, MS, MG> virtualColumn(double value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.data.addVirtualFieldData(virtualFieldData);
        return this;
    }

    @Override
    public Map<String, String> getColumnAliasMap() {
        return this.data.getMainTableData().getColumnAliasMap();
    }

    @Override
    public String getColumnSql() {
        return this.columnParser.parse(this.getData().getMainTableData(),
                this.getData().getFunctionColumnDataList(),
                this.getData().getVirtualFieldDataSet(),
                this.getData().getColumnDataSet());
    }

}
