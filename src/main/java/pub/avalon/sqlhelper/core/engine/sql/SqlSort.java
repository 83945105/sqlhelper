package pub.avalon.sqlhelper.core.engine.sql;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.SqlSortBean;
import pub.avalon.sqlhelper.core.beans.SqlSortBeanJoin;
import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.engine.SortEngine;
import pub.avalon.sqlhelper.core.engine.callback.SortCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
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
        this.tableAlias = tableAlias;
    }

    private List<SqlSortBean<TS>> sqlSortBeans = new ArrayList<>(1);

    @Override
    public SqlSort<TS> sort(SortHelper<?>... sortHelpers) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper).setSortHelpers(sortHelpers));
        return this;
    }

    @Override
    public SqlSort<TS> sort(SortCallback<TS> sortCallback) {
        this.sqlSortBeans.add(new SqlSortBean<>(this.sortHelper).setSortCallback(sortCallback));
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
        this.sqlSortBeans.add(new SqlSortBeanJoin<S, SJ, SC, SW, SG, SH, SS, TS>(this.sortHelper)
                .setTableHelperClass(tableHelperClass)
                .setTableAlias(tableAlias)
                .setSortCallbackJoin(sortCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SqlSortBean<TS>> getSqlSortBeans() {
        return sqlSortBeans;
    }
}
