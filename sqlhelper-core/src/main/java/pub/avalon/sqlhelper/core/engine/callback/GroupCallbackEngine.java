package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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
    <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
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
    default <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, GroupCallback<SG> groupCallback) {
        return groupBy(tableHelperClass, null, groupCallback);
    }

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableGroupDatum execute(Class<F> tableHelperClass, String tableAlias, GroupCallback<FG> callback, SqlBuilderOptions sqlBuilderOptions) {
        return GroupCallback.execute(tableHelperClass, tableAlias, callback, sqlBuilderOptions);
    }
}