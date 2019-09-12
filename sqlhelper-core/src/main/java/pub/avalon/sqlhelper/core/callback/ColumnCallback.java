package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.List;

/**
 * @author baichao
 */
@FunctionalInterface
public interface ColumnCallback<TC extends ColumnHelper<TC>> {

    TC apply(TC table);

    static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDatum execute(boolean mainTable, Class<T> tableHelperClass, String tableAlias, ColumnCallback<TC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.singleTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(mainTable, tc, columnCallback, sqlBuilderOptions);
    }

    static <TC extends ColumnHelper<TC>> TableColumnDatum execute(boolean mainTable, TC columnHelper, ColumnCallback<TC> columnCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (columnHelper == null) {
            ExceptionUtils.columnHelperNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        columnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        columnHelper = columnCallback.apply(columnHelper);
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            if (mainTable && !sqlBuilderOptions.getSqlPartDatumBuilderOptions().isSelectAllColumnForMainTable()) {
                return null;
            }
            if (!mainTable && !sqlBuilderOptions.getSqlPartDatumBuilderOptions().isSelectAllColumnForJoinTable()) {
                return null;
            }
            columnData = BeanUtils.getColumnData(columnHelper);
        }
        return new TableColumnDatum(columnHelper.getTableAlias(), columnData);
    }
}