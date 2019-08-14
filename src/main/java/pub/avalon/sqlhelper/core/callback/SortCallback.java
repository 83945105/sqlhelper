package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SortCallback<T extends SortHelper<T>> {

    T apply(T table);

    static <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> TableSortDatum execute(Class<F> tableHelperClass, String tableAlias, SortCallback<FS> sortCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        F f = BeanUtils.tableHelper(tableHelperClass);
        tableAlias = tableAlias == null ? f.getTableAlias() : tableAlias;
        FS fs = f.newSortHelper(tableAlias);
        return execute(fs, sortCallback, sqlBuilderOptions);
    }

    static <FS extends SortHelper<FS>> TableSortDatum execute(FS sortHelper, SortCallback<FS> sortCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (sortHelper == null) {
            ExceptionUtils.sortHelperNullException();
        }
        if (sortCallback == null) {
            return null;
        }
        // 设置配置开始
        sortHelper.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        sortHelper = sortCallback.apply(sortHelper);
        List<SortDatum> sortData = sortHelper.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return null;
        }
        return new TableSortDatum(sortHelper.getTableAlias(), sortData);
    }
}
