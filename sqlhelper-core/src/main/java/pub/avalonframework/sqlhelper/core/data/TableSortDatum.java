package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableSortDatum {

    private String tableAlias;

    private List<SortDatum> sortData;

    public TableSortDatum(String tableAlias, List<SortDatum> sortData) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.sortData = sortData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SortDatum> getSortData() {
        return sortData;
    }
}