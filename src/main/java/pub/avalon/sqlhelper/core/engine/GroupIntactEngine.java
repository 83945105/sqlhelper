package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.Set;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupIntactEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends SortIntactEngine<T, TO, TC, TW, TG, TS> {

    public GroupIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public GroupIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public GroupIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public GroupIntactEngine<T, TO, TC, TW, TG, TS> group(GroupHelper<?>... groupSqlModels) {
        if (groupSqlModels == null || groupSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    public GroupIntactEngine<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
        MainTableData<T> mainTableData = this.getSqlData().getMainTableData();
        TG tg = BeanUtils.tableModel(this.tableModelClass).newGroupHelper();
        tg = callback.apply(tg);
        Set<GroupDatum> groupData = tg.takeoutSqlModelData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum(mainTableData, groupData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> GroupIntactEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableModelClass, String alias, GroupCallback<SG> callback) {
        JoinTableData<S> joinTableData = this.getSqlData().getJoinTableData(alias, tableModelClass);
        SG sg = BeanUtils.tableModel(tableModelClass).newGroupHelper();
        sg = callback.apply(sg);
        Set<GroupDatum> groupData = sg.takeoutSqlModelData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum(joinTableData, groupData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> GroupIntactEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableModelClass, GroupCallback<SG> callback) {
        return group(tableModelClass, null, callback);
    }

}
