package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.engine.SortEngine;
import pub.avalon.sqlhelper.core.engine.builder.beans.AbstractSqlSortBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlSortBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlSortBeanJoin;
import pub.avalon.sqlhelper.core.engine.callback.SortCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 * @date 2019/7/17
 */
public class SqlSort<TS extends SortHelper<TS>> implements SortEngine<SqlSort<TS>>, SortCallbackEngine<TS, SqlSort<TS>> {

    private TS sortHelper;
    private String tableAlias;

    {
        this.sortHelper = BeanUtils.getSortHelper(this);
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
    public SqlSort<TS> sort(SortHelper<?>... sortHelpers) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper, this.tableAlias).setSortHelpers(sortHelpers));
        return this;
    }

    @Override
    public SqlSort<TS> sort(SortCallback<TS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper, this.tableAlias).setSortCallback(sortCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlSort<TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBeanJoin<>(tableHelperClass, tableAlias, sortCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlSortBean> getSqlSortBeans() {
        return sqlSortBeans;
    }

    public List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return execute(this, sqlBuilderOptions);
    }

    public static <FS extends SortHelper<FS>> List<TableSortDatum> execute(SqlSort<FS> sqlSort, SqlBuilderOptions sqlBuilderOptions) {
        List<TableSortDatum> tableSortData = new ArrayList<>();
        sqlSort.getSqlSortBeans().forEach(sqlSortBean -> tableSortData.addAll(sqlSortBean.execute(sqlBuilderOptions)));
        return tableSortData;
    }
}
