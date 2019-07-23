package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public final class SqlSortBeanJoin<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, SS extends SortHelper<SS>> extends SqlSortBean<SS> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private SortCallback<TS> sortCallbackJoin;

    public SqlSortBeanJoin(SS sortHelper) {
        super(sortHelper);
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public SqlSortBeanJoin<T, TJ, TC, TW, TG, TH, TS, SS> setTableHelperClass(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public SqlSortBeanJoin<T, TJ, TC, TW, TG, TH, TS, SS> setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public SortCallback<TS> getSortCallbackJoin() {
        return sortCallbackJoin;
    }

    public SqlSortBeanJoin<T, TJ, TC, TW, TG, TH, TS, SS> setSortCallbackJoin(SortCallback<TS> sortCallbackJoin) {
        this.sortCallbackJoin = sortCallbackJoin;
        return this;
    }

    @Override
    public List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(SortCallback.execute(this.tableHelperClass, this.tableAlias, this.sortCallbackJoin, sqlBuilderOptions));
    }

}
