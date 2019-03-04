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
public class GroupIntactEngine<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>> extends SortIntactEngine<M, ML, MO, MW, MS, MG> {

    GroupIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    GroupIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    GroupIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public GroupIntactEngine<M, ML, MO, MW, MS, MG> group(Group<M, ML, MO, MW, MS, MG> group) {
        List<String> columns = group.apply(this.sqlData.getMainTableData().getTableModel().getGroupModel()).getColumns();
        if (columns == null || columns.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.sqlData.getMainTableData();
        this.sqlData.addGroupData(new GroupData(tableData, columns));
        return this;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> GroupIntactEngine<M, ML, MO, MW, MS, MG> group(Class<T> groupClass, String alias, Group<T, TL, TO, TW, TS, TG> group) {
        List<String> columns = group.apply(this.sqlData.getJoinTableData(alias, groupClass).getTableModel().getGroupModel()).getColumns();
        if (columns == null || columns.size() == 0) {
            return this;
        }
        AbstractTableData tableData = this.sqlData.getJoinTableData(alias, groupClass);
        this.sqlData.addGroupData(new GroupData(tableData, columns));
        return this;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> GroupIntactEngine<M, ML, MO, MW, MS, MG> group(Class<T> groupClass, Group<T, TL, TO, TW, TS, TG> group) {
        return group(groupClass, null, group);
    }

}
