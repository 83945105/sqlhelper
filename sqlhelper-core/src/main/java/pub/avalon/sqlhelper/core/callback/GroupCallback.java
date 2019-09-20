package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.List;

/**
 * @author baichao
 */
@FunctionalInterface
public interface GroupCallback<T extends GroupHelper<T>> {

    T apply(T table);

    static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableGroupDatum execute(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> groupCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TG tg = t.newGroupHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tg, groupCallback, sqlBuilderOptions);
    }

    static <TG extends GroupHelper<TG>> TableGroupDatum execute(TG groupHelper, GroupCallback<TG> groupCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (groupHelper == null) {
            ExceptionUtils.groupHelperNullException();
        }
        if (groupCallback == null) {
            return null;
        }
        // 设置配置开始
        groupHelper.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        groupHelper = groupCallback.apply(groupHelper);
        List<GroupDatum> groupData = groupHelper.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return null;
        }
        return new TableGroupDatum(groupHelper.getTableAlias(), groupData);
    }
}