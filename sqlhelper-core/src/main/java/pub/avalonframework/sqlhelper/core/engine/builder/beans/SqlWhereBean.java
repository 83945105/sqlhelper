package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.WhereCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableWhereDatum;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlWhereBean<TW extends WhereHelper<TW>> extends AbstractSqlWhereBean {

    private TW whereHelper;

    private WhereHelper<?>[] whereHelpers;

    private WhereCallback<TW> whereCallback;

    public SqlWhereBean(TW whereHelper, String tableAlias) {
        super(tableAlias);
        this.whereHelper = whereHelper;
    }

    public SqlWhereBean<TW> setWhereHelpers(WhereHelper<?>[] whereHelpers) {
        this.whereHelpers = whereHelpers;
        return this;
    }

    public SqlWhereBean<TW> setWhereCallback(WhereCallback<TW> whereCallback) {
        this.whereCallback = whereCallback;
        return this;
    }

    @Override
    public List<TableWhereDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableWhereDatum> tableWhereData = new ArrayList<>(1);
        if (this.whereHelpers != null) {
            for (WhereHelper<?> whereHelper : this.whereHelpers) {
                tableWhereData.add(whereHelper.execute());
            }
        }
        if (this.whereCallback != null) {
            tableWhereData.add(CallbackExecutor.execute(this.whereHelper, this.whereCallback, sqlBuilderOptions));
        }
        return tableWhereData;
    }
}