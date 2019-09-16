package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlColumnBean<TC extends ColumnHelper<TC>> extends AbstractSqlColumnBean {

    private TC columnHelper;

    private ColumnHelper<?>[] columnHelpers;

    private ColumnCallback<TC> columnCallback;

    public SqlColumnBean(TC columnHelper, String tableAlias) {
        super(tableAlias);
        this.columnHelper = columnHelper;
    }

    public SqlColumnBean<TC> setColumnHelpers(ColumnHelper<?>[] columnHelpers) {
        this.columnHelpers = columnHelpers;
        return this;
    }

    public SqlColumnBean<TC> setColumnCallback(ColumnCallback<TC> columnCallback) {
        this.columnCallback = columnCallback;
        return this;
    }

    @Override
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