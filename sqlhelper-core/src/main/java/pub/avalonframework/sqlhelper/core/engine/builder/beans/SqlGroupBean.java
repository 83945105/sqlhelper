package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableGroupDatum;
import pub.avalonframework.sqlhelper.core.helper.GroupHelper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
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
            tableGroupData.add(CallbackExecutor.execute(this.groupHelper, this.groupCallback, sqlBuilderOptions));
        }
        return tableGroupData;
    }
}