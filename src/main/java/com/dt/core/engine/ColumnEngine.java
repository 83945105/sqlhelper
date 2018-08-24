package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.build.SqlBuilder;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.Column;
import com.dt.core.norm.Model;
import com.dt.core.sql.Insert;

import java.util.Collection;
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
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends SqlEngine<M> implements Insert<SqlBuilder> {

    public ColumnEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public ColumnEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @SuppressWarnings("unchecked")
    public ColumnEngine<M, ML, MO, MC, MS, MG> column(Column<M, ML, MO, MC, MS, MG> column) {
        MainTableData tableData = this.sqlData.getMainTableData();
        Map<String, String> columns = column.apply((ML) tableData.getTableModel().getColumnModel()).getColumnAliasMap();
        if (columns.size() == 0) {
            columns = tableData.getTableModel().getColumnAliasMap();
        }
        tableData.addColumnAliasMap(columns);
        this.sqlData.addColumnData(tableData);
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Object[] args) {
        return this.sqlBuilderProxy.insertArgs(args);
    }

    @Override
    public SqlBuilder insertArgs(Collection<?> args) {
        return this.sqlBuilderProxy.insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object record) {
        return this.sqlBuilderProxy.insertJavaBean(record);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object record) {
        return this.sqlBuilderProxy.insertJavaBeanSelective(record);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Object[] records) {
        return this.sqlBuilderProxy.batchInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> records) {
        return this.sqlBuilderProxy.batchInsertJavaBeans(records);
    }

}
