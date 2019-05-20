package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.TableColumnData;
import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.Set;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends SqlEngine<T, TO, TC, TW, TG, TS> {

    public ColumnEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public ColumnEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public ColumnEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback) {
        MainTableData<T> mainTableData = this.getSqlData().getMainTableData();
        TC tc = BeanUtils.tableModel(this.tableModelClass).newColumnSqlModel();
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlModelData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = mainTableData.buildTableColumnData();
        }
        this.getSqlData().addTableColumnData(new TableColumnData(mainTableData, columnData));
        return this;
    }

}
