package pub.avalon.sqlhelper.core.beans;

import pub.avalon.holygrail.utils.GenericsUtils;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableDatum;
import pub.avalon.sqlhelper.core.engine.builder.*;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author baichao
 */
public final class BeanUtils {

    private BeanUtils() {
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
