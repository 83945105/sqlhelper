package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.build.SqlBuilder;
import com.dt.core.data.FunctionColumnData;
import com.dt.core.data.JoinTableData;
import com.dt.core.data.MainTableData;
import com.dt.core.data.VirtualFieldData;
import com.dt.core.norm.Column;
import com.dt.core.norm.Model;
import com.dt.core.sql.QueryByPrimaryKey;

import java.util.Map;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends WhereIntactEngine<M, ML, MO, MC, MS, MG> implements QueryByPrimaryKey<SqlBuilder> {

    ColumnIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    ColumnIntactEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @SuppressWarnings("unchecked")
    public ColumnIntactEngine<M, ML, MO, MC, MS, MG> column(Column<M, ML, MO, MC, MS, MG> column) {
        MainTableData tableData = this.sqlData.getMainTableData();
        Map<String, String> columns = column.apply((ML) tableData.getTableModel().getColumnModel()).getColumnAliasMap();
        if (columns.size() == 0) {
            columns = tableData.getTableModel().getColumnAliasMap();
        }
        tableData.addColumnAliasMap(columns);
        this.sqlData.addColumnData(tableData);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> column(Class<T> columnClass, String alias, Column<T, TL, TO, TC, TS, TG> column) {
        JoinTableData joinTableData = this.sqlData.getJoinTableData(alias, columnClass);
        Map<String, String> columns = column.apply((TL) joinTableData.getTableModel().getColumnModel()).getColumnAliasMap();
        if (columns.size() == 0) {
            columns = joinTableData.getTableModel().getColumnAliasMap();
        }
        joinTableData.addColumnAliasMap(columns);
        this.sqlData.addColumnData(joinTableData);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> column(Class<T> columnClass, Column<T, TL, TO, TC, TS, TG> column) {
        return column(columnClass, null, column);
    }

    public ColumnIntactEngine<M, ML, MO, MC, MS, MG> virtualColumn(String value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, ML, MO, MC, MS, MG> virtualColumn(int value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, ML, MO, MC, MS, MG> virtualColumn(long value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, ML, MO, MC, MS, MG> virtualColumn(double value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    @SuppressWarnings("unchecked")
    public ColumnIntactEngine<M, ML, MO, MC, MS, MG> functionColumn(FunctionColumnType functionColumnType, Column<M, ML, MO, MC, MS, MG> column) {
        MainTableData tableData = this.sqlData.getMainTableData();
        Map<String, String> columns = column.apply((ML) tableData.getTableModel().getColumnModel()).getColumnAliasMap();
        this.sqlData.addFunctionColumnData(new FunctionColumnData(tableData, functionColumnType, columns));
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> functionColumn(Class<T> columnClass, String alias, FunctionColumnType functionColumnType, Column<T, TL, TO, TC, TS, TG> column) {
        JoinTableData joinTableData = this.sqlData.getJoinTableData(alias, columnClass);
        Map<String, String> columns = column.apply((TL) joinTableData.getTableModel().getColumnModel()).getColumnAliasMap();
        this.sqlData.addFunctionColumnData(new FunctionColumnData(joinTableData, functionColumnType, columns));
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> functionColumn(Class<T> columnClass, FunctionColumnType functionColumnType, Column<T, TL, TO, TC, TS, TG> column) {
        return functionColumn(columnClass, null, functionColumnType, column);
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilderProxy.queryByPrimaryKey(keyValue);
    }

}
