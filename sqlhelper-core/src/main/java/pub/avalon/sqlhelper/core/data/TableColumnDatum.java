package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableColumnDatum {

    private String tableAlias;

    private List<ColumnDatum> columnData;

    public TableColumnDatum(String tableAlias, List<ColumnDatum> columnData) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.columnData = columnData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ColumnDatum> getColumnData() {
        return columnData;
    }
}