package pub.avalon.sqlhelper.core.beans;

import pub.avalon.holygrail.utils.GenericsUtils;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableDatum;
import pub.avalon.sqlhelper.core.engine.*;
import pub.avalon.sqlhelper.core.helper.*;

import java.lang.reflect.ParameterizedType;
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
    public static Set<ColumnDatum> getColumnData(ColumnHelper columnHelper) {
        Set<ColumnDatum> columnData = new LinkedHashSet<>();
        Set<TableColumn> tableColumns = columnHelper.getTableDefaultColumns();
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

    @SuppressWarnings("unchecked")
    public static <T extends JoinHelper<T>> Class<T> getJoinHelperClass(SqlJoin<T> sqlJoin) {
        return GenericsUtils.getExpectAncestorsClassGenricType(sqlJoin.getClass(), JoinHelper.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends JoinHelper<T>> T getJoinHelper(SqlJoin<T> sqlJoin) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(getJoinHelperClass(sqlJoin));
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> Class<T> getColumnHelperClass(SqlColumn<T> sqlColumn) {
        return GenericsUtils.getExpectAncestorsClassGenricType(sqlColumn.getClass(), ColumnHelper.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T getColumnHelper(SqlColumn<T> sqlColumn) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(getColumnHelperClass(sqlColumn));
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T extends WhereHelper<T>> Class<T> getWhereHelperClass(SqlWhere<T> sqlWhere) {
        return GenericsUtils.getExpectAncestorsClassGenricType(sqlWhere.getClass(), WhereHelper.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends WhereHelper<T>> T getWhereHelper(SqlWhere<T> sqlWhere) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(getWhereHelperClass(sqlWhere));
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T extends GroupHelper<T>> Class<T> getGroupHelperClass(SqlGroup<T> sqlGroup) {
        return GenericsUtils.getExpectAncestorsClassGenricType(sqlGroup.getClass(), GroupHelper.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends GroupHelper<T>> T getGroupHelper(SqlGroup<T> sqlGroup) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(getGroupHelperClass(sqlGroup));
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T extends HavingHelper<T>> Class<T> getHavingHelperClass(SqlHaving<T> sqlHaving) {
        return GenericsUtils.getExpectAncestorsClassGenricType(sqlHaving.getClass(), HavingHelper.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends HavingHelper<T>> T getHavingHelper(SqlHaving<T> sqlHaving) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(getHavingHelperClass(sqlHaving));
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T extends SortHelper<T>> Class<T> getSortHelperClass(SqlSort<T> sqlSort) {
        return GenericsUtils.getExpectAncestorsClassGenricType(sqlSort.getClass(), SortHelper.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends SortHelper<T>> T getSortHelper(SqlSort<T> sqlSort) {
        Object obj = org.springframework.beans.BeanUtils.instantiateClass(getSortHelperClass(sqlSort));
        return (T) obj;
    }

}
