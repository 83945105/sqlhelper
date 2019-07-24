package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface SortCallback<T extends SortHelper<T>> {

    /**
     * 接收排序助手
     *
     * @param table {@link SortHelper}
     * @return {@link SortHelper}
     */
    T apply(T table);

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableSortDatum execute(Class<F> tableHelperClass, String tableAlias, SortCallback<FS> callback, SqlBuilderOptions sqlBuilderOptions) {
        F f = BeanUtils.tableHelper(tableHelperClass);
        tableAlias = tableAlias == null ? f.getTableAlias() : tableAlias;
        FS fs = f.newSortHelper(tableAlias);
        // 设置配置开始
        fs.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        fs = callback.apply(fs);
        List<SortDatum> sortData = fs.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return null;
        }
        return new TableSortDatum(tableAlias, sortData);
    }

    static <FS extends SortHelper<FS>> TableSortDatum execute(FS sortHelper, SortCallback<FS> callback, SqlBuilderOptions sqlBuilderOptions) {
        // 设置配置开始
        sortHelper.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        sortHelper = callback.apply(sortHelper);
        List<SortDatum> sortData = sortHelper.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return null;
        }
        return new TableSortDatum(sortHelper.getTableAlias(), sortData);
    }

}
