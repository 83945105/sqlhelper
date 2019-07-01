package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableDatum;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 白超
 * @date 2019/5/19
 */
public final class BeanUtils {

    private BeanUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T tableHelper(Class<T> clazz) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(clazz);
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T tableHelper(TableDatum tableDatum) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(tableDatum.getTableHelperClass());
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static Set<ColumnDatum> getColumnData(TableHelper tableHelper) {
        Set<ColumnDatum> columnData = new LinkedHashSet<>();
        Set<TableColumn> tableColumns = tableHelper.getTableColumns();
        if (tableColumns == null) {
            return columnData;
        }
        for (TableColumn tableColumn : tableColumns) {
            columnData.add(new ColumnDatum(tableColumn.getTableName(), tableColumn.getTableAlias(), tableColumn.getName(), tableColumn.getAlias(), tableColumn.getAlias()));
        }
        return columnData;
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> Set<ColumnDatum> getColumnData(Class<T> clazz) {
        return getColumnData(tableHelper(clazz));
    }

    @SuppressWarnings("unchecked")
    public static Set<ColumnDatum> getColumnData(TableDatum tableDatum) {
        Class clazz = tableDatum.getTableHelperClass();
        return getColumnData(clazz);
    }

}
