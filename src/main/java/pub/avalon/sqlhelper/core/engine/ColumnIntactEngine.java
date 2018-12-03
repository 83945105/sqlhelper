package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.data.FunctionColumnData;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.VirtualFieldData;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.norm.Column;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.SubQuery;
import pub.avalon.sqlhelper.core.sql.Query;
import pub.avalon.sqlhelper.core.sql.QueryByPrimaryKey;
import pub.avalon.sqlhelper.core.sql.UpdateByPrimaryKey;

import java.util.Collection;
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
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends WhereIntactEngine<M, ML, MO, MC, MS, MG> implements QueryByPrimaryKey, UpdateByPrimaryKey {

    ColumnIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    ColumnIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    ColumnIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
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
        for (Map.Entry<String, String> entry : columns.entrySet()) {
            this.sqlData.addFunctionColumnData(new FunctionColumnData(tableData, functionColumnType, entry.getKey(), entry.getValue()));
        }
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
        for (Map.Entry<String, String> entry : columns.entrySet()) {
            this.sqlData.addFunctionColumnData(new FunctionColumnData(joinTableData, functionColumnType, entry.getKey(), entry.getValue()));
        }
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

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> subQuery(String tableName, Class<T> mainClass, String alias, SubQuery<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> subQuery, String columnAlias) {
        QueryEngine<T, TL, TO, TC, TS, TG> queryEngine;
        switch (this.sqlData.getDataBaseType()) {
            case MYSQL:
                queryEngine = new QueryEngine<>(tableName, mainClass, alias, DataBaseType.MYSQL);
                break;
            case SQLSERVER:
                queryEngine = new QueryEngine<>(tableName, mainClass, alias, DataBaseType.SQLSERVER);
                break;
            default:
                throw new SqlException("SubQuery do not support this database type temporarily.");
        }
        for (Map.Entry<String, JoinTableData> entry : this.sqlData.getJoinTableDataAliasMap().entrySet()) {
            queryEngine.sqlData.addSubQueryJoinTableData(entry.getValue());
        }
        MainTableData tableData = this.sqlData.getMainTableData();
        MC mc = (MC) tableData.getTableModel().getWhereModel();
        Query query = subQuery.apply(mc, queryEngine);
        this.sqlData.addSubQueryAliasMap(columnAlias, query);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> subQuery(String tableName, Class<T> mainClass, SubQuery<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> subQuery, String columnAlias) {
        return this.subQuery(tableName, mainClass, null, subQuery, columnAlias);
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> subQuery(Class<T> mainClass, SubQuery<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> subQuery, String columnAlias) {
        return this.subQuery(null, mainClass, null, subQuery, columnAlias);
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> ColumnIntactEngine<M, ML, MO, MC, MS, MG> subQuery(Class<T> mainClass, String alias, SubQuery<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> subQuery, String columnAlias) {
        return this.subQuery(null, mainClass, alias, subQuery, columnAlias);
    }

    @Override
    public SqlBuilder queryByPrimaryKey(Object keyValue) {
        return this.sqlBuilderProxy.queryByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Collection<?> args) {
        return this.sqlBuilderProxy.updateArgsByPrimaryKey(keyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKey(keyValue, javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.updateOrInsertJavaBeans(javaBeans);
    }

}
