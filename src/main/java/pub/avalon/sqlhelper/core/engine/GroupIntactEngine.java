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
public class GroupIntactEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends SortIntactEngine<M, MC, MO, MW, MS, MG> {

    GroupIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    GroupIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    GroupIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public GroupIntactEngine<M, MC, MO, MW, MS, MG> group(Group<M, MC, MO, MW, MS, MG> group) {
        List<String> columns = group.apply(this.sqlData.getMainTableData().getTableModel().getGroupModel()).getColumns();
        if (columns == null || columns.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.sqlData.getMainTableData();
        this.sqlData.addGroupData(new GroupData(tableData, columns));
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> GroupIntactEngine<M, MC, MO, MW, MS, MG> group(Class<T> groupClass, String alias, Group<T, TC, TO, TW, TS, TG> group) {
        List<String> columns = group.apply(this.sqlData.getJoinTableData(alias, groupClass).getTableModel().getGroupModel()).getColumns();
        if (columns == null || columns.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.sqlData.getJoinTableData(alias, groupClass);
        this.sqlData.addGroupData(new GroupData(tableData, columns));
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> GroupIntactEngine<M, MC, MO, MW, MS, MG> group(Class<T> groupClass, Group<T, TC, TO, TW, TS, TG> group) {
        return group(groupClass, null, group);
    }

}
