package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * 列引擎
 *
 * @author baichao
 * @since 2018/7/10
 */
public interface ColumnEngine<R> extends Engine {

    /**
     * 设置列
     *
     * @param columnHelpers {@link ColumnHelper}
     * @return {@link ColumnEngine}
     */
    R column(ColumnHelper<?>... columnHelpers);

    /**
     * 虚拟列
     *
     * @param columnValue 值
     * @param columnAlias 别名
     * @return {@link ColumnEngine}
     */
    R virtualColumn(Object columnValue, String columnAlias);

}
