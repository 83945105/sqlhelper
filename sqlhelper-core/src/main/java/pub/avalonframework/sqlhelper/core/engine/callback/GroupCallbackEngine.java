package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.engine.Engine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface GroupCallbackEngine<TG extends GroupHelper<TG>, R> extends Engine {

    /**
     * use callback to add group sql data
     *
     * @param groupCallback {@link GroupCallback}
     * @return R
     */
    R groupBy(GroupCallback<TG> groupCallback);

    /**
     * use callback to add assign class group sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param groupCallback    {@link GroupCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback);

    /**
     * use callback to add assign class group sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param groupCallback    {@link GroupCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, GroupCallback<SG> groupCallback) {
        return groupBy(tableHelperClass, null, groupCallback);
    }
}