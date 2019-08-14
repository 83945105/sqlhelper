package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * 表排序数据
 *
 * @author baichao
 * @since 2018/7/10
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
