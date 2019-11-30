package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.SortCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableSortDatum;
import pub.avalonframework.sqlhelper.core.helper.SortHelper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
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
            tableSortData.add(CallbackExecutor.execute(this.sortHelper, this.sortCallback, sqlBuilderOptions));
        }
        return tableSortData;
    }
}