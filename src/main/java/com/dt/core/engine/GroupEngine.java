package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.GroupData;
import com.dt.core.data.AbstractTableData;
import com.dt.core.norm.Group;
import com.dt.core.norm.Model;

import java.util.Collection;
import java.util.List;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends SortEngine<M, ML, MO, MC, MS, MG> {

    GroupEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    GroupEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    public GroupEngine<M, ML, MO, MC, MS, MG> group(String... columnNames) {
        if (columnNames == null || columnNames.length == 0) {
            return this;
        }
        AbstractTableData tableData = this.data.getMainTableData();
        tableData.addGroupColumns(columnNames);
        this.data.addGroupData(new GroupData(tableData, columnNames));
        return this;
    }

    public GroupEngine<M, ML, MO, MC, MS, MG> group(Collection<String> columnNames) {
        if (columnNames == null || columnNames.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.data.getMainTableData();
        tableData.addGroupColumns(columnNames);
        this.data.addGroupData(new GroupData(tableData, columnNames.toArray(new String[columnNames.size()])));
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupEngine<M, ML, MO, MC, MS, MG> group(String alias, Class<T> columnClass, String... columnNames) {
        if (columnNames == null || columnNames.length == 0) {
            return this;
        }
        AbstractTableData tableData = this.data.getJoinTableData(alias, columnClass);
        tableData.addGroupColumns(columnNames);
        this.data.addGroupData(new GroupData(tableData, columnNames));
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupEngine<M, ML, MO, MC, MS, MG> group(String alias, Class<T> columnClass, Collection<String> columnNames) {
        if (columnNames == null || columnNames.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.data.getJoinTableData(alias, columnClass);
        tableData.addGroupColumns(columnNames);
        this.data.addGroupData(new GroupData(tableData, columnNames.toArray(new String[columnNames.size()])));
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupEngine<M, ML, MO, MC, MS, MG> group(Class<T> columnClass, String... columnNames) {
        return group(null, columnClass, columnNames);
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupEngine<M, ML, MO, MC, MS, MG> group(Class<T> columnClass, Collection<String> columnNames) {
        return group(null, columnClass, columnNames);
    }

    @SuppressWarnings("unchecked")
    public GroupEngine<M, ML, MO, MC, MS, MG> group(Group<M, ML, MO, MC, MS, MG> group) {
        List<String> columns = group.apply((MG) this.data.getMainTableData().getTableModel().getGroupModel()).getColumns();
        return group(columns);
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupEngine<M, ML, MO, MC, MS, MG> group(Class<T> groupClass, String alias, Group<T, TL, TO, TC, TS, TG> group) {
        List<String> columns = group.apply((TG) this.data.getJoinTableData(alias, groupClass).getTableModel().getGroupModel()).getColumns();
        return group(alias, groupClass, columns);
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupEngine<M, ML, MO, MC, MS, MG> group(Class<T> groupClass, Group<T, TL, TO, TC, TS, TG> group) {
        return group(groupClass, null, group);
    }

}
