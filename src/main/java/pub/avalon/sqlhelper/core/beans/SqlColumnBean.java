package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public class SqlColumnBean<TC extends ColumnHelper<TC>> {

    protected TC columnHelper;

    public SqlColumnBean(TC columnHelper) {
        this.columnHelper = columnHelper;
    }

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

    public List<TableColumnDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        List<TableColumnDatum> tableColumnData = new ArrayList<>(1);
        if (this.columnHelpers != null) {
            for (ColumnHelper<?> columnHelper : this.columnHelpers) {
                tableColumnData.add(columnHelper.execute());
            }
        }
        if (this.columnCallback != null) {
            tableColumnData.add(ColumnCallback.execute(this.columnHelper, this.columnCallback, sqlBuilderOptions));
        }
        return tableColumnData;
    }

}
