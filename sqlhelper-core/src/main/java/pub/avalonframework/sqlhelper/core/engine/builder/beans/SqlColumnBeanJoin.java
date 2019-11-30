package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlColumnBeanJoin<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractSqlColumnBean {

    private Class<T> tableHelperClass;

    private ColumnCallback<TC> columnCallback;

    public SqlColumnBeanJoin(Class<T> tableHelperClass, String tableAlias, ColumnCallback<TC> columnCallback) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.columnCallback = columnCallback;
    }

    @Override
    public List<TableColumnDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, this.columnCallback, sqlBuilderOptions));
    }
}