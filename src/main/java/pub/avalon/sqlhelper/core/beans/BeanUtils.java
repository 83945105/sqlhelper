package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.ColumnDatum;
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

    public static <T extends TableHelper> T tableHelper(Class<T> clazz) {
        return org.springframework.beans.BeanUtils.instantiateClass(clazz);
    }

    public static <T extends TableHelper> Set<ColumnDatum> getColumnData(T tableHelper) {
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

}
