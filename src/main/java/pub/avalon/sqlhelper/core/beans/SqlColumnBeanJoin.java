package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public final class SqlColumnBeanJoin<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, SC extends ColumnHelper<SC>> extends SqlColumnBean<SC> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private ColumnCallback<TC> columnCallbackJoin;

    public SqlColumnBeanJoin(SC columnHelper) {
        super(columnHelper);
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public SqlColumnBeanJoin<T, TJ, TC, TW, TG, TH, TS, SC> setTableHelperClass(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public SqlColumnBeanJoin<T, TJ, TC, TW, TG, TH, TS, SC> setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public ColumnCallback<TC> getColumnCallbackJoin() {
        return columnCallbackJoin;
    }

    public SqlColumnBeanJoin<T, TJ, TC, TW, TG, TH, TS, SC> setColumnCallbackJoin(ColumnCallback<TC> columnCallbackJoin) {
        this.columnCallbackJoin = columnCallbackJoin;
        return this;
    }

    @Override
    public List<TableColumnDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(ColumnCallback.execute(this.tableHelperClass, this.tableAlias, this.columnCallbackJoin, sqlBuilderOptions));
    }

}
