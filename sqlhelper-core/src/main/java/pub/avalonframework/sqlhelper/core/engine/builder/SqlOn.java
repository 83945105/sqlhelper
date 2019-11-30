package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.callback.OnCallback;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataProducer;
import pub.avalonframework.sqlhelper.core.engine.OnEngine;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.AbstractSqlOnBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlOnBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlOnBeanJoin;
import pub.avalonframework.sqlhelper.core.engine.callback.OnCallbackEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class SqlOn<TO extends OnHelper<TO>> implements OnEngine<SqlOn<TO>>, OnCallbackEngine<TO, SqlOn<TO>> {

    private TO onHelper;
    private String tableAlias;

    {
        this.onHelper = HelperManager.findOnHelperClassFromAncestorsGenericType(this);
    }

    public SqlOn() {
        this.tableAlias = this.onHelper.getTableAlias();
    }

    public SqlOn(String tableAlias) {
        this.onHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlOnBean> sqlOnBeans = new ArrayList<>(1);

    @Override
    public SqlOn<TO> on(OnHelper<?>... onHelpers) {
        this.sqlOnBeans.add(new SqlOnBean<>(this.onHelper, this.tableAlias).setOnHelpers(onHelpers));
        return this;
    }

    @Override
    public SqlOn<TO> on(OnCallback<TO> onCallback) {
        this.sqlOnBeans.add(new SqlOnBean<>(this.onHelper, this.tableAlias).setOnCallback(onCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlOn<TO> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.sqlOnBeans.add(new SqlOnBeanJoin<>(this.onHelper, tableHelperClass, tableAlias, onJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlOnBean> getSqlOnBeans() {
        return sqlOnBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FO extends OnHelper<FO>> void execute(SqlOn<FO> sqlOn, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataProducer sqlDataProducer = supplier.get();
        if (sqlDataProducer == null) {
            return;
        }
        sqlOn.getSqlOnBeans().forEach(sqlOnBean -> sqlOnBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addTableOnDatum));
    }
}