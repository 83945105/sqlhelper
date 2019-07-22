package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.helper.WhereHelper;
import pub.avalon.sqlhelper.core.helper.WhereHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public class SqlWhereBean<TW extends WhereHelper<TW>> {

    protected TW whereHelper;

    public SqlWhereBean(TW whereHelper) {
        this.whereHelper = whereHelper;
    }

    private WhereHelper<?>[] whereHelpers;

    private WhereCallback<TW> whereCallback;

    public WhereHelper<?>[] getWhereHelpers() {
        return whereHelpers;
    }

    public SqlWhereBean<TW> setWhereHelpers(WhereHelper<?>[] whereHelpers) {
        this.whereHelpers = whereHelpers;
        return this;
    }

    public WhereCallback<TW> getWhereCallback() {
        return whereCallback;
    }

    public SqlWhereBean<TW> setWhereCallback(WhereCallback<TW> whereCallback) {
        this.whereCallback = whereCallback;
        return this;
    }

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
