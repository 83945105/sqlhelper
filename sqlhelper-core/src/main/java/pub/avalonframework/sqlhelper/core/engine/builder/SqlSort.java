package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.callback.SortCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataProducer;
import pub.avalonframework.sqlhelper.core.engine.SortEngine;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.AbstractSqlSortBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlSortBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlSortBeanJoin;
import pub.avalonframework.sqlhelper.core.engine.callback.SortCallbackEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public class SqlSort<TS extends SortHelper<TS>> implements SortEngine<SqlSort<TS>>, SortCallbackEngine<TS, SqlSort<TS>> {

    private TS sortHelper;
    private String tableAlias;

    {
        this.sortHelper = HelperManager.findSortHelperClassFromAncestorsGenericType(this);
    }

    public SqlSort() {
        this.tableAlias = this.sortHelper.getTableAlias();
    }

    public SqlSort(String tableAlias) {
        this.sortHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlSortBean> sqlSortBeans = new ArrayList<>(1);

    @Override
    public SqlSort<TS> orderBy(SortHelper<?>... sortHelpers) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper, this.tableAlias).setSortHelpers(sortHelpers));
        return this;
    }

    @Override
    public SqlSort<TS> orderBy(SortCallback<TS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper, this.tableAlias).setSortCallback(sortCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlSort<TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBeanJoin<>(tableHelperClass, tableAlias, sortCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlSortBean> getSqlSortBeans() {
        return sqlSortBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FS extends SortHelper<FS>> void execute(SqlSort<FS> sqlSort, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataProducer sqlDataProducer = supplier.get();
        if (sqlDataProducer == null) {
            return;
        }
        sqlSort.getSqlSortBeans().forEach(sqlSortBean -> sqlSortBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addTableSortDatum));
    }
}