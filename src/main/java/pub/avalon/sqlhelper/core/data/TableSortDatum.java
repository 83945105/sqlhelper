package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.Set;

/**
 * 表排序数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class TableSortDatum<T extends TableHelper> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private Set<SortDatum> sortData;

    public TableSortDatum(Class<T> tableHelperClass, String tableAlias, Set<SortDatum> sortData) {
        if (tableHelperClass == null) {
            throw new RuntimeException("tableHelperClass can not be null.");
        }
        if (tableAlias == null) {
            throw new RuntimeException("tableAlias can not be null.");
        }
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.sortData = sortData;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Set<SortDatum> getSortData() {
        return sortData;
    }

}
