package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.SortHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 * @date 2019/7/17
 */
public final class SqlSortBean<TG extends SortHelper<TG>> extends AbstractSqlSortBean {

    private TG sortHelper;

    private SortHelper<?>[] sortHelpers;

    private SortCallback<TG> sortCallback;

    public SqlSortBean(TG sortHelper, String tableAlias) {
        super(tableAlias);
        this.sortHelper = sortHelper;
    }

    public SqlSortBean<TG> setSortHelpers(SortHelper<?>[] sortHelpers) {
        this.sortHelpers = sortHelpers;
        return this;
    }

    public SqlSortBean<TG> setSortCallback(SortCallback<TG> sortCallback) {
        this.sortCallback = sortCallback;
        return this;
    }

    @Override
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
