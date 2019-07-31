package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.GroupHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface GroupEngine<R> extends Engine {

    R group(GroupHelper<?>... groupHelpers);

    static List<TableGroupDatum> execute(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(groupHelpers).map(groupHelper -> groupHelper.execute()).collect(Collectors.toList());
    }

}
