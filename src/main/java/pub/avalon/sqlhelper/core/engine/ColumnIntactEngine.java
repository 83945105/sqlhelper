package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.norm.Column;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.SubQuery;
import pub.avalon.sqlhelper.core.sql.QueryByPrimaryKey;
import pub.avalon.sqlhelper.core.sql.UpdateByPrimaryKey;

import java.util.Collection;
import java.util.Set;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnIntactEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends WhereIntactEngine<M, MC, MO, MW, MS, MG> implements QueryByPrimaryKey, UpdateByPrimaryKey {

    public ColumnIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public ColumnIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    public ColumnIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public ColumnIntactEngine<M, MC, MO, MW, MS, MG> column(Column<M, MC, MO, MW, MS, MG> column) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MC mc = mainTableData.getTableModel().getColumnModel();
        mc.setSqlData(this.sqlData);
        mc = column.apply(mc);
        Set<ColumnDatum> columnData = mc.modelDataBuilder.takeoutModelData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = mainTableData.buildTableColumnData();
        }
        this.sqlData.addTableColumnData(new TableColumnData(mainTableData, columnData));
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> column(Class<T> columnClass, String alias, Column<T, TC, TO, TW, TS, TG> column) {
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, columnClass);
        TC tc = joinTableData.getTableModel().getColumnModel();
        tc = column.apply(tc);
        Set<ColumnDatum> columnData = tc.modelDataBuilder.takeoutModelData();
        if (columnData == null || columnData.size() == 0) {
            columnData = joinTableData.buildTableColumnData();
        }
        this.sqlData.addTableColumnData(new TableColumnData(joinTableData, columnData));
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> column(Class<T> columnClass, Column<T, TC, TO, TW, TS, TG> column) {
        return column(columnClass, null, column);
    }

    public ColumnIntactEngine<M, MC, MO, MW, MS, MG> virtualColumn(String value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, MC, MO, MW, MS, MG> virtualColumn(int value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, MC, MO, MW, MS, MG> virtualColumn(long value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, MC, MO, MW, MS, MG> virtualColumn(double value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<M, MC, MO, MW, MS, MG> functionColumn(FunctionColumnType functionColumnType, Column<M, MC, MO, MW, MS, MG> column) {
        if (functionColumnType == null) {
            return this;
        }
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MC mc = mainTableData.getTableModel().getColumnModel();
        mc = column.apply(mc);
        Set<ColumnDatum> columnData = mc.modelDataBuilder.takeoutModelData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        for (ColumnDatum columnDatum : columnData) {
            this.sqlData.addFunctionColumnData(new FunctionColumnData(mainTableData, functionColumnType, columnDatum.getOwnerColumnName(), columnDatum.getOwnerColumnAlias()));
        }
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> functionColumn(Class<T> columnClass, String alias, FunctionColumnType functionColumnType, Column<T, TC, TO, TW, TS, TG> column) {
        if (functionColumnType == null) {
            return this;
        }
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, columnClass);
        TC tc = joinTableData.getTableModel().getColumnModel();
        tc = column.apply(tc);
        Set<ColumnDatum> columnData = tc.modelDataBuilder.takeoutModelData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        for (ColumnDatum columnDatum : columnData) {
            this.sqlData.addFunctionColumnData(new FunctionColumnData(joinTableData, functionColumnType, columnDatum.getOwnerColumnName(), columnDatum.getOwnerColumnAlias()));
        }
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> functionColumn(Class<T> columnClass, FunctionColumnType functionColumnType, Column<T, TC, TO, TW, TS, TG> column) {
        return functionColumn(columnClass, null, functionColumnType, column);
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> subQuery(String tableName, Class<T> mainClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery, String columnAlias) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.sqlData, tableName, mainClass, alias, subQuery);
        this.sqlData.addSubQueryData(columnAlias, sqlBuilder);
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> subQuery(String tableName, Class<T> mainClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery, String columnAlias) {
        return this.subQuery(tableName, mainClass, null, subQuery, columnAlias);
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> subQuery(Class<T> mainClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery, String columnAlias) {
        return this.subQuery(null, mainClass, null, subQuery, columnAlias);
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> ColumnIntactEngine<M, MC, MO, MW, MS, MG> subQuery(Class<T> mainClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery, String columnAlias) {
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
