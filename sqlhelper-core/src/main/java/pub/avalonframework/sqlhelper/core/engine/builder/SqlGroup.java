package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataProducer;
import pub.avalonframework.sqlhelper.core.engine.GroupEngine;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.AbstractSqlGroupBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlGroupBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlGroupBeanJoin;
import pub.avalonframework.sqlhelper.core.engine.callback.GroupCallbackEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class SqlGroup<TG extends GroupHelper<TG>> implements GroupEngine<SqlGroup<TG>>, GroupCallbackEngine<TG, SqlGroup<TG>> {

    private TG groupHelper;
    private String tableAlias;

    {
        this.groupHelper = HelperManager.findGroupHelperClassFromAncestorsGenericType(this);
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
    public SqlGroup<TG> groupBy(GroupHelper<?>... groupHelpers) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper, this.tableAlias).setGroupHelpers(groupHelpers));
        return this;
    }

    @Override
    public SqlGroup<TG> groupBy(GroupCallback<TG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBean<>(this.groupHelper, this.tableAlias).setGroupCallback(groupCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlGroup<TG> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.sqlGroupBeans.add(new SqlGroupBeanJoin<>(tableHelperClass, tableAlias, groupCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlGroupBean> getSqlGroupBeans() {
        return sqlGroupBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FG extends GroupHelper<FG>> void execute(SqlGroup<FG> sqlGroup, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataProducer sqlDataProducer = supplier.get();
        if (sqlDataProducer == null) {
            return;
        }
        sqlGroup.getSqlGroupBeans().forEach(sqlGroupBean -> sqlGroupBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addTableGroupDatum));
    }
}