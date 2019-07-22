package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    static List<TableGroupDatum> execute(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(groupHelpers).map(groupHelper -> groupHelper.execute()).collect(Collectors.toList());
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
