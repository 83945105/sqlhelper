package pub.avalon.sqlhelper.core.engine.builder.beans;

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
        return Collections.singletonList(ColumnCallback.execute(this.tableHelperClass, this.tableAlias, this.columnCallback, sqlBuilderOptions));
    }

}
