package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.modelbuilder.*;
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
public class ColumnIntactEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends WhereIntactEngine<T, TO, TC, TW, TG, TS> implements QueryByPrimaryKey, UpdateByPrimaryKey {

    public ColumnIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public ColumnIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public ColumnIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> column(ColumnSqlModel<?>... columnSqlModels) {
        if (columnSqlModels == null || columnSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback) {
        MainTableData<T> mainTableData = this.sqlData.getMainTableData();
        TC tc = mainTableData.getTableModel().newColumnSqlModel();
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlModelData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = mainTableData.buildTableColumnData();
        }
        this.sqlData.addTableColumnData(new TableColumnData(mainTableData, columnData));
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableModelClass, String alias, ColumnCallback<SC> callback) {
        JoinTableData<S> joinTableData = this.sqlData.getJoinTableData(alias, tableModelClass);
        SC sc = joinTableData.getTableModel().newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            columnData = joinTableData.buildTableColumnData();
        }
        this.sqlData.addTableColumnData(new TableColumnData(joinTableData, columnData));
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableModelClass, ColumnCallback<SC> callback) {
        return column(tableModelClass, null, callback);
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> virtualColumn(String value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> virtualColumn(int value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> virtualColumn(long value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> virtualColumn(double value, String alias) {
        VirtualFieldData virtualFieldData = new VirtualFieldData();
        virtualFieldData.setValue(value);
        virtualFieldData.setAlias(alias);
        this.sqlData.addVirtualFieldData(virtualFieldData);
        return this;
    }

    public ColumnIntactEngine<T, TO, TC, TW, TG, TS> functionColumn(FunctionColumnType functionColumnType, ColumnCallback<TC> callback) {
        if (functionColumnType == null) {
            return this;
        }
        MainTableData<T> mainTableData = this.sqlData.getMainTableData();
        TC tc = mainTableData.getTableModel().newColumnSqlModel();
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlModelData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        for (ColumnDatum columnDatum : columnData) {
            this.sqlData.addFunctionColumnData(new FunctionColumnData(mainTableData, functionColumnType, columnDatum.getOwnerColumnName(), columnDatum.getOwnerColumnAlias()));
        }
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> columnClass, String alias, FunctionColumnType functionColumnType, ColumnCallback<SC> callback) {
        if (functionColumnType == null) {
            return this;
        }
        JoinTableData<S> joinTableData = this.sqlData.getJoinTableData(alias, columnClass);
        SC sc = joinTableData.getTableModel().newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        for (ColumnDatum columnDatum : columnData) {
            this.sqlData.addFunctionColumnData(new FunctionColumnData(joinTableData, functionColumnType, columnDatum.getOwnerColumnName(), columnDatum.getOwnerColumnAlias()));
        }
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableModelClass, FunctionColumnType functionColumnType, ColumnCallback<SC> callback) {
        return functionColumn(tableModelClass, null, functionColumnType, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableModelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.sqlData, tableName, tableModelClass, alias, callback);
        this.sqlData.addSubQueryData(columnAlias, sqlBuilder);
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableModelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.sqlData, tableName, tableModelClass, alias, callback);
        this.sqlData.addSubQueryData(columnAlias, sqlBuilder);
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableModelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(tableName, tableModelClass, null, callback, columnAlias);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> subQuery(Class<S> tableModelClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableModelClass, null, callback, columnAlias);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> ColumnIntactEngine<T, TO, TC, TW, TG, TS> subQuery(Class<S> tableModelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableModelClass, alias, callback, columnAlias);
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
