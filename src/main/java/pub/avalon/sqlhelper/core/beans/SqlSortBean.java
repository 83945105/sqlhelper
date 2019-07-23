package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.SortHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public class SqlSortBean<TG extends SortHelper<TG>> {

    protected TG sortHelper;

    public SqlSortBean(TG sortHelper) {
        this.sortHelper = sortHelper;
    }

    private SortHelper<?>[] sortHelpers;

    private SortCallback<TG> sortCallback;

    public SortHelper<?>[] getSortHelpers() {
        return sortHelpers;
    }

    public SqlSortBean<TG> setSortHelpers(SortHelper<?>[] sortHelpers) {
        this.sortHelpers = sortHelpers;
        return this;
    }

    public SortCallback<TG> getSortCallback() {
        return sortCallback;
    }

    public SqlSortBean<TG> setSortCallback(SortCallback<TG> sortCallback) {
        this.sortCallback = sortCallback;
        return this;
    }

    public List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableSortDatum> tableSortData = new ArrayList<>(1);
        if (this.sortHelpers != null) {
            for (SortHelper<?> sortHelper : this.sortHelpers) {
                tableSortData.add(sortHelper.execute());
            }
        }
        if (this.sortCallback != null) {
            tableSortData.add(SortCallback.execute(this.sortHelper, this.sortCallback, sqlBuilderOptions));
        }
        return tableSortData;
    }

}
