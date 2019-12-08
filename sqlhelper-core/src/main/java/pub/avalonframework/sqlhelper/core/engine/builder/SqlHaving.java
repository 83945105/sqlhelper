package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.callback.HavingCallback;
import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataProducer;
import pub.avalonframework.sqlhelper.core.engine.HavingEngine;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.AbstractSqlHavingBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlHavingBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlHavingBeanJoin;
import pub.avalonframework.sqlhelper.core.engine.callback.HavingCallbackEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class SqlHaving<TH extends HavingHelper<TH>> implements HavingEngine<SqlHaving<TH>>, HavingCallbackEngine<TH, SqlHaving<TH>> {

    private TH havingHelper;
    private String tableAlias;

    {
        this.havingHelper = HelperManager.findHavingHelperClassFromAncestorsGenericType(this);
    }

    public SqlHaving() {
        this.tableAlias = this.havingHelper.getTableAlias();
    }

    public SqlHaving(String tableAlias) {
        this.havingHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlHavingBean> sqlHavingBeans = new ArrayList<>(1);

    @Override
    public SqlHaving<TH> having(HavingHelper<?>... havingHelpers) {
        this.sqlHavingBeans.add(new SqlHavingBean<>(this.havingHelper, this.tableAlias).setHavingHelpers(havingHelpers));
        return this;
    }

    @Override
    public SqlHaving<TH> having(HavingCallback<TH> havingCallback) {
        this.sqlHavingBeans.add(new SqlHavingBean<>(this.havingHelper, this.tableAlias).setHavingCallback(havingCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHaving<TH> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        this.sqlHavingBeans.add(new SqlHavingBeanJoin<>(this.havingHelper, tableHelperClass, tableAlias, havingJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlHavingBean> getSqlHavingBeans() {
        return sqlHavingBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FW extends HavingHelper<FW>> void execute(SqlHaving<FW> sqlHaving, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataProducer sqlDataProducer = supplier.get();
        if (sqlDataProducer == null) {
            return;
        }
        sqlHaving.getSqlHavingBeans().forEach(sqlHavingBean -> sqlHavingBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addTableHavingDatum));
    }
}