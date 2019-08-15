package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.engine.GroupEngine;
import pub.avalon.sqlhelper.core.engine.builder.beans.AbstractSqlGroupBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlGroupBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlGroupBeanJoin;
import pub.avalon.sqlhelper.core.engine.callback.GroupCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class SqlGroup<TG extends GroupHelper<TG>> implements GroupEngine<SqlGroup<TG>>, GroupCallbackEngine<TG, SqlGroup<TG>> {

    private TG groupHelper;
    private String tableAlias;

    {
        this.groupHelper = BeanUtils.getGroupHelper(this);
    }

    public SqlGroup() {
        this.tableAlias = this.groupHelper.getTableAlias();
    }

    public SqlGroup(String tableAlias) {
        this.groupHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlGroupBean> sqlGroupBeans = new ArrayList<>(1);

    @Override
    public SqlGroup<TG> group(GroupHelper<?>... groupHelpers) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper, this.tableAlias).setGroupHelpers(groupHelpers));
        return this;
    }

    @Override
    public SqlGroup<TG> group(GroupCallback<TG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper, this.tableAlias).setGroupCallback(groupCallback));
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
        this.sqlGroupBeans.add(new SqlGroupBeanJoin<>(tableHelperClass, tableAlias, groupCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlGroupBean> getSqlGroupBeans() {
        return sqlGroupBeans;
    }

    public List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return execute(this, sqlBuilderOptions);
    }

    public static <FG extends GroupHelper<FG>> List<TableGroupDatum> execute(SqlGroup<FG> sqlGroup, SqlBuilderOptions sqlBuilderOptions) {
        List<TableGroupDatum> tableGroupData = new ArrayList<>();
        sqlGroup.getSqlGroupBeans().forEach(sqlGroupBean -> tableGroupData.addAll(sqlGroupBean.execute(sqlBuilderOptions)));
        return tableGroupData;
    }
}