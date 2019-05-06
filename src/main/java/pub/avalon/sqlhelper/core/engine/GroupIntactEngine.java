package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.TableGroupData;
import pub.avalon.sqlhelper.core.norm.Group;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.Set;

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
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MG mg = mainTableData.getTableModel().getGroupModel();
        mg = group.apply(mg);
        Set<GroupDatum> groupData = mg.groupDataBuilder.takeoutModelData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.sqlData.addTableGroupData(new TableGroupData(mainTableData, groupData));
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> GroupIntactEngine<M, MC, MO, MW, MS, MG> group(Class<T> groupClass, String alias, Group<T, TC, TO, TW, TS, TG> group) {
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, groupClass);
        TG tg = joinTableData.getTableModel().getGroupModel();
        tg = group.apply(tg);
        Set<GroupDatum> groupData = tg.groupDataBuilder.takeoutModelData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.sqlData.addTableGroupData(new TableGroupData(joinTableData, groupData));
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
