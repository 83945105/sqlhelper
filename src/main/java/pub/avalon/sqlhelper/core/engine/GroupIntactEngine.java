package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.TableGroupData;
import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.Set;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupIntactEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends SortIntactEngine<T, TO, TC, TW, TG, TS> {

    public GroupIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public GroupIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public GroupIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public GroupIntactEngine<T, TO, TC, TW, TG, TS> group(GroupSqlModel<?>... groupSqlModels) {
        if (groupSqlModels == null || groupSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    public GroupIntactEngine<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
        MainTableData<T> mainTableData = this.sqlData.getMainTableData();
        TG tg = mainTableData.getTableModel().newGroupSqlModel();
        tg = callback.apply(tg);
        Set<GroupDatum> groupData = tg.takeoutSqlModelData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.sqlData.addTableGroupData(new TableGroupData(mainTableData, groupData));
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> GroupIntactEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableModelClass, String alias, GroupCallback<SG> callback) {
        JoinTableData<S> joinTableData = this.sqlData.getJoinTableData(alias, tableModelClass);
        SG sg = joinTableData.getTableModel().newGroupSqlModel();
        sg = callback.apply(sg);
        Set<GroupDatum> groupData = sg.takeoutSqlModelData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.sqlData.addTableGroupData(new TableGroupData(joinTableData, groupData));
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> GroupIntactEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableModelClass, GroupCallback<SG> callback) {
        return group(tableModelClass, null, callback);
    }

}
