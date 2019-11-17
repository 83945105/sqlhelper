package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.HavingCallback;
import pub.avalon.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalon.sqlhelper.core.data.TableHavingDatum;
import pub.avalon.sqlhelper.core.helper.HavingHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlHavingBean<TW extends HavingHelper<TW>> extends AbstractSqlHavingBean {

    private TW havingHelper;

    private HavingHelper<?>[] havingHelpers;

    private HavingCallback<TW> havingCallback;

    public SqlHavingBean(TW havingHelper, String tableAlias) {
        super(tableAlias);
        this.havingHelper = havingHelper;
    }

    public SqlHavingBean<TW> setHavingHelpers(HavingHelper<?>[] havingHelpers) {
        this.havingHelpers = havingHelpers;
        return this;
    }

    public SqlHavingBean<TW> setHavingCallback(HavingCallback<TW> havingCallback) {
        this.havingCallback = havingCallback;
        return this;
    }

    @Override
    public List<TableHavingDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableHavingDatum> tableHavingData = new ArrayList<>(1);
        if (this.havingHelpers != null) {
            for (HavingHelper<?> havingHelper : this.havingHelpers) {
                tableHavingData.add(havingHelper.execute());
            }
        }
        if (this.havingCallback != null) {
            tableHavingData.add(CallbackExecutor.execute(this.havingHelper, this.havingCallback, sqlBuilderOptions));
        }
        return tableHavingData;
    }
}