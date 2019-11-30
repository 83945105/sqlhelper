package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface ColumnEngine<R> extends Engine {

    /**
     * add column sql data
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return R
     */
    R column(ColumnHelper<?>... columnHelpers);

    /**
     * add select column sql data
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return R
     */
    R select(ColumnHelper<?>... columnHelpers);

    /**
     * add insert column sql data
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return R
     */
    R insert(ColumnHelper<?>... columnHelpers);

    /**
     * add update column sql data
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return R
     */
    R update(ColumnHelper<?>... columnHelpers);

    /**
     * add virtual column sql data
     *
     * @param columnValue column value
     * @param columnAlias column alias
     * @return R
     */
    R virtualColumn(Object columnValue, String columnAlias);
}