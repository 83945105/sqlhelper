package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * @author 白超
 * @date 2019/7/17
 */
public class SqlColumnBean<TC extends ColumnHelper<TC>> {

    private ColumnHelper<?>[] columnHelpers;

    private ColumnCallback<TC> columnCallback;

    public ColumnHelper<?>[] getColumnHelpers() {
        return columnHelpers;
    }

    public SqlColumnBean<TC> setColumnHelpers(ColumnHelper<?>[] columnHelpers) {
        this.columnHelpers = columnHelpers;
        return this;
    }

    public ColumnCallback<TC> getColumnCallback() {
        return columnCallback;
    }

    public SqlColumnBean<TC> setColumnCallback(ColumnCallback<TC> columnCallback) {
        this.columnCallback = columnCallback;
        return this;
    }
}
