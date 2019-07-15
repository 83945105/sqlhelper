package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Set;

/**
 * 表排序数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class TableSortDatum {

    private String tableAlias;

    private Set<SortDatum> sortData;

    public TableSortDatum(String tableAlias, Set<SortDatum> sortData) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.sortData = sortData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Set<SortDatum> getSortData() {
        return sortData;
    }

}
