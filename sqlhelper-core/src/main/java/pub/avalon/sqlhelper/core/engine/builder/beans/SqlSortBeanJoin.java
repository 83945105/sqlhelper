package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlSortBeanJoin<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractSqlSortBean {

    private Class<T> tableHelperClass;

    private SortCallback<TS> sortCallback;

    public SqlSortBeanJoin(Class<T> tableHelperClass, String tableAlias, SortCallback<TS> sortCallback) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.sortCallback = sortCallback;
    }

    @Override
    public List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(SortCallback.execute(this.tableHelperClass, this.tableAlias, this.sortCallback, sqlBuilderOptions));
    }
}