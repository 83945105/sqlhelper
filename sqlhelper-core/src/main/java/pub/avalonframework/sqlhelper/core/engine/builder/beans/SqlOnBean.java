package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.OnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlOnBean<TO extends OnHelper<TO>> extends AbstractSqlOnBean {

    private TO onHelper;

    private OnHelper<?>[] onHelpers;

    private OnCallback<TO> onCallback;

    public SqlOnBean(TO onHelper, String tableAlias) {
        super(tableAlias);
        this.onHelper = onHelper;
    }

    public SqlOnBean<TO> setOnHelpers(OnHelper<?>[] onHelpers) {
        this.onHelpers = onHelpers;
        return this;
    }

    public SqlOnBean<TO> setOnCallback(OnCallback<TO> onCallback) {
        this.onCallback = onCallback;
        return this;
    }

    @Override
    public List<TableOnDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableOnDatum> tableOnData = new ArrayList<>(1);
        if (this.onHelpers != null) {
            for (OnHelper<?> onHelper : this.onHelpers) {
                tableOnData.add(onHelper.execute());
            }
        }
        if (this.onCallback != null) {
            tableOnData.add(CallbackExecutor.execute(this.onHelper, this.onCallback, sqlBuilderOptions));
        }
        return tableOnData;
    }
}