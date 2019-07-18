package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface GroupEngine<TG extends GroupHelper<TG>, R extends GroupEngine<TG, R>> {

    R group(GroupHelper<?>... groupHelpers);

    R group(GroupCallback<TG> callback);

    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback);

    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R group(Class<S> tableHelperClass, GroupCallback<SG> callback) {
        return group(tableHelperClass, null, callback);
    }

}
