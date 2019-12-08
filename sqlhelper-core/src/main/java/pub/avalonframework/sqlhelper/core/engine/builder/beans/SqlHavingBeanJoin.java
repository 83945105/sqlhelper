package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableHavingDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class SqlHavingBeanJoin<TH extends HavingHelper<TH>,
        S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractSqlHavingBean {

    private TH havingHelper;

    private Class<S> tableHelperClass;

    private HavingJoinCallback<TH, SH> havingJoinCallback;

    public SqlHavingBeanJoin(TH havingHelper, Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        super(tableAlias);
        this.havingHelper = havingHelper;
        this.tableHelperClass = tableHelperClass;
        this.havingJoinCallback = havingJoinCallback;
    }

    @Override
    public List<TableHavingDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(CallbackExecutor.execute(this.havingHelper, this.tableHelperClass, this.tableAlias, this.havingJoinCallback, sqlBuilderOptions));
    }
}