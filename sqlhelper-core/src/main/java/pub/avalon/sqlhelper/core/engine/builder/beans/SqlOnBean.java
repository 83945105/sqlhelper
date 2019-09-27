package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.helper.OnHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public class SqlOnBean<TO extends OnHelper<TO>, SO extends OnHelper<SO>> extends AbstractSqlOnBean {

    private TO mainOnHelper;

    private SO joinOnHelper;

    private OnHelper<?>[] onHelpers;

    private OnCallback<TO, SO> onCallback;

    public SqlOnBean(TO mainOnHelper, SO joinOnHelper, String tableAlias) {
        super(tableAlias);
        this.mainOnHelper = mainOnHelper;
        this.joinOnHelper = joinOnHelper;
    }

    public SqlOnBean<TO, SO> setOnHelpers(OnHelper<?>[] onHelpers) {
        this.onHelpers = onHelpers;
        return this;
    }

    public SqlOnBean<TO, SO> setOnCallback(OnCallback<TO, SO> onCallback) {
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
            tableOnData.add(OnCallback.execute(this.mainOnHelper, this.joinOnHelper, this.onCallback, sqlBuilderOptions));
        }
        return tableOnData;
    }
}