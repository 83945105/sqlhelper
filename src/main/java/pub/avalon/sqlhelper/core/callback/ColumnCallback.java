package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Set;

/**
 * 列回调
 *
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface ColumnCallback<TC extends ColumnHelper<TC>> {

    /**
     * 接收列助手
     *
     * @param table {@link ColumnHelper}
     * @return {@link ColumnHelper}
     */
    TC apply(TC table);

    /**
     * 执行
     *
     * @param tableHelperClass  表助手
     * @param tableAlias        表别名
     * @param columnCallback    列回调
     * @param sqlBuilderOptions Sql构建配置
     * @return {@link TableColumnDatum}
     */
    static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDatum execute(Class<T> tableHelperClass, String tableAlias, ColumnCallback<TC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        T t = BeanUtils.tableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias = tableAlias == null ? t.getTableAlias() : tableAlias);
        // 设置配置开始
        tc.setSqlBuilderOptions(sqlBuilderOptions);
        // 设置配置结束
        tc = columnCallback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(tableHelperClass);
        }
        return new TableColumnDatum(tableAlias, columnData);
    }

}
