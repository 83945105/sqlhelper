package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<TableColumnDatum> execute() {
        List<TableColumnDatum> tableColumnData = new ArrayList<>(1);
        ColumnHelper<?>[] columnHelpers = this.getColumnHelpers();
        if (columnHelpers != null) {
            for (ColumnHelper<?> columnHelper : columnHelpers) {
                tableColumnData.add(columnHelper.execute());
            }
        }
        ColumnCallback<TC> columnCallback = this.getColumnCallback();
        if (columnCallback != null) {
            tableColumnData.add(ColumnCallback.execute());
        }
    }

}
