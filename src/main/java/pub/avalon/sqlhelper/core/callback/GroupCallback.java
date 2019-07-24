package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author 白超
 * @date 2019/5/18
 */
@FunctionalInterface
public interface GroupCallback<T extends GroupHelper<T>> {

    /**
     * 接收分组助手
     *
     * @param table {@link GroupHelper}
     * @return {@link GroupHelper}
     */
    T apply(T table);

    static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableGroupDatum execute(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> callback, SqlBuilderOptions sqlBuilderOptions) {
        T t = BeanUtils.tableHelper(tableHelperClass);
        tableAlias = tableAlias == null ? t.getTableAlias() : tableAlias;
        TG tg = t.newGroupHelper(tableAlias);
        // 设置配置开始
        tg.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        tg = callback.apply(tg);
        List<GroupDatum> groupData = tg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return null;
        }
        return new TableGroupDatum(tableAlias, groupData);
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
