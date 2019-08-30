package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

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
     * add virtual column sql data
     *
     * @param columnValue column value
     * @param columnAlias column alias
     * @return R
     */
    R virtualColumn(Object columnValue, String columnAlias);
}