package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.GroupHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public class SqlGroupBean<TG extends GroupHelper<TG>> {

    protected TG groupHelper;

    public SqlGroupBean(TG groupHelper) {
        this.groupHelper = groupHelper;
    }

    private GroupHelper<?>[] groupHelpers;

    private GroupCallback<TG> groupCallback;

    public GroupHelper<?>[] getGroupHelpers() {
        return groupHelpers;
    }

    public SqlGroupBean<TG> setGroupHelpers(GroupHelper<?>[] groupHelpers) {
        this.groupHelpers = groupHelpers;
        return this;
    }

    public GroupCallback<TG> getGroupCallback() {
        return groupCallback;
    }

    public SqlGroupBean<TG> setGroupCallback(GroupCallback<TG> groupCallback) {
        this.groupCallback = groupCallback;
        return this;
    }

    public List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableGroupDatum> tableGroupData = new ArrayList<>(1);
        if (this.groupHelpers != null) {
            for (GroupHelper<?> groupHelper : this.groupHelpers) {
                tableGroupData.add(groupHelper.execute());
            }
        }
        if (this.groupCallback != null) {
            tableGroupData.add(GroupCallback.execute(this.groupHelper, this.groupCallback, sqlBuilderOptions));
        }
        return tableGroupData;
    }

}
