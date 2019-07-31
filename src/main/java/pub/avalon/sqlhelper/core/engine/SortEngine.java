package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.SortHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排序引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface SortEngine<R> extends Engine {

    R sort(SortHelper<?>... sortHelpers);

    static List<TableSortDatum> execute(SortHelper<?>... sortHelpers) {
        if (sortHelpers == null || sortHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(sortHelpers).map(sortHelper -> sortHelper.execute()).collect(Collectors.toList());
    }
}
