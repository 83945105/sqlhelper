package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.helper.WhereHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
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
            tableWhereData.add(WhereCallback.execute(this.whereHelper, this.whereCallback, sqlBuilderOptions));
        }
        return tableWhereData;
    }

}
