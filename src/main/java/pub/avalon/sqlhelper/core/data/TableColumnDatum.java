package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.Set;

/**
 * 表列数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class TableColumnDatum<T extends TableHelper> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private Set<ColumnDatum> columnData;

    public TableColumnDatum(Class<T> tableHelperClass, String tableAlias, Set<ColumnDatum> columnData) {
        if (tableHelperClass == null) {
            throw new RuntimeException("tableHelperClass can not be null.");
        }
        if (tableAlias == null) {
            throw new RuntimeException("tableAlias can not be null.");
        }
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.columnData = columnData;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Set<ColumnDatum> getColumnData() {
        return columnData;
    }

}
