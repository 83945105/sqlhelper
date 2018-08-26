package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.GroupData;
import pub.avalon.sqlhelper.core.data.AbstractTableData;
import pub.avalon.sqlhelper.core.norm.Group;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.List;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends SortIntactEngine<M, ML, MO, MC, MS, MG> {

    GroupIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    GroupIntactEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @SuppressWarnings("unchecked")
    public GroupIntactEngine<M, ML, MO, MC, MS, MG> group(Group<M, ML, MO, MC, MS, MG> group) {
        List<String> columns = group.apply((MG) this.sqlData.getMainTableData().getTableModel().getGroupModel()).getColumns();
        if (columns == null || columns.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.sqlData.getMainTableData();
        tableData.addGroupColumns(columns);
        this.sqlData.addGroupData(new GroupData(tableData, columns));
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupIntactEngine<M, ML, MO, MC, MS, MG> group(Class<T> groupClass, String alias, Group<T, TL, TO, TC, TS, TG> group) {
        List<String> columns = group.apply((TG) this.sqlData.getJoinTableData(alias, groupClass).getTableModel().getGroupModel()).getColumns();
        if (columns == null || columns.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.sqlData.getJoinTableData(alias, groupClass);
        tableData.addGroupColumns(columns);
        this.sqlData.addGroupData(new GroupData(tableData, columns));
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> GroupIntactEngine<M, ML, MO, MC, MS, MG> group(Class<T> groupClass, Group<T, TL, TO, TC, TS, TG> group) {
        return group(groupClass, null, group);
    }

}
