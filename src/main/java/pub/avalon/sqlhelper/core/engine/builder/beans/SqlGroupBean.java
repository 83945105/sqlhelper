package pub.avalon.sqlhelper.core.engine.builder.beans;

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
public final class SqlGroupBean<TG extends GroupHelper<TG>> extends AbstractSqlGroupBean {

    private TG groupHelper;

    private GroupHelper<?>[] groupHelpers;

    private GroupCallback<TG> groupCallback;

    public SqlGroupBean(TG groupHelper, String tableAlias) {
        super(tableAlias);
        this.groupHelper = groupHelper;
    }

    public SqlGroupBean<TG> setGroupHelpers(GroupHelper<?>[] groupHelpers) {
        this.groupHelpers = groupHelpers;
        return this;
    }

    public SqlGroupBean<TG> setGroupCallback(GroupCallback<TG> groupCallback) {
        this.groupCallback = groupCallback;
        return this;
    }

    @Override
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
