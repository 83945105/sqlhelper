package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
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

    static List<TableColumnDatum> execute(ColumnHelper<?>... columnHelpers) {
        if (columnHelpers == null || columnHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(columnHelpers).map(columnHelper -> columnHelper.execute()).collect(Collectors.toList());
    }

}
