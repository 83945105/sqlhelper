package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlWhereBeanJoin<TW extends WhereHelper<TW>,
        S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
        SJ extends JoinHelper<SJ>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractSqlWhereBean {

    private TW whereHelper;

    private Class<S> tableHelperClass;

    private WhereJoinCallback<TW, SW> whereJoinCallback;

    public SqlWhereBeanJoin(TW whereHelper, Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        super(tableAlias);
        this.whereHelper = whereHelper;
        this.tableHelperClass = tableHelperClass;
        this.whereJoinCallback = whereJoinCallback;
    }

    @Override
    public List<TableWhereDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(WhereJoinCallback.execute(this.whereHelper, this.tableHelperClass, this.tableAlias, this.whereJoinCallback, sqlBuilderOptions));
    }
}