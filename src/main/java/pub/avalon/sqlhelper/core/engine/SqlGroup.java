package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.SqlGroupBean;
import pub.avalon.sqlhelper.core.beans.SqlGroupBeanJoin;
import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlGroup<TG extends GroupHelper<TG>> implements GroupEngine<TG, SqlGroup<TG>> {

    private TG groupHelper;
    private String tableAlias;

    {
        this.groupHelper = BeanUtils.getGroupHelper(this);
    }

    public SqlGroup() {
        this.tableAlias = this.groupHelper.getTableAlias();
    }

    public SqlGroup(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    private List<SqlGroupBean<TG>> sqlGroupBeans = new ArrayList<>(1);

    @Override
    public SqlGroup<TG> group(GroupHelper<?>... groupHelpers) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper).setGroupHelpers(groupHelpers));
        return this;
    }

    @Override
    public SqlGroup<TG> group(GroupCallback<TG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper).setGroupCallback(groupCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlGroup<TG> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBeanJoin<S, SJ, SC, SW, SG, SH, SS, TG>(this.groupHelper).setTableHelperClass(tableHelperClass).setTableAlias(tableAlias).setGroupCallbackJoin(groupCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SqlGroupBean<TG>> getSqlGroupBeans() {
        return sqlGroupBeans;
    }
}
