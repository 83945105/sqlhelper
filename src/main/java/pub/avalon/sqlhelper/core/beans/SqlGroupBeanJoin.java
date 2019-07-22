package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public final class SqlGroupBeanJoin<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, SG extends GroupHelper<SG>> extends SqlGroupBean<SG> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private GroupCallback<TG> groupCallbackJoin;

    public SqlGroupBeanJoin(SG groupHelper) {
        super(groupHelper);
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public SqlGroupBeanJoin<T, TJ, TC, TW, TG, TH, TS, SG> setTableHelperClass(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public SqlGroupBeanJoin<T, TJ, TC, TW, TG, TH, TS, SG> setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public GroupCallback<TG> getGroupCallbackJoin() {
        return groupCallbackJoin;
    }

    public SqlGroupBeanJoin<T, TJ, TC, TW, TG, TH, TS, SG> setGroupCallbackJoin(GroupCallback<TG> groupCallbackJoin) {
        this.groupCallbackJoin = groupCallbackJoin;
        return this;
    }

    @Override
    public List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(GroupCallback.execute(this.tableHelperClass, this.tableAlias, this.groupCallbackJoin, sqlBuilderOptions));
    }

}
