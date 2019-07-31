package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.helper.WhereHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface WhereEngine<R> extends Engine {

    R where(WhereHelper<?>... whereHelpers);

    static List<TableWhereDatum> execute(WhereHelper<?>... whereHelpers) {
        if (whereHelpers == null || whereHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(whereHelpers).map(whereHelper -> whereHelper.execute()).collect(Collectors.toList());
    }

}
