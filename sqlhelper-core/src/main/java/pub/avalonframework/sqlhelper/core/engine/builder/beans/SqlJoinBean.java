package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;
import pub.avalonframework.sqlhelper.core.engine.callback.executor.CallbackEngineExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class SqlJoinBean<TO extends OnHelper<TO>,
        S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractSqlJoinBean {

    private TO mainOnHelper;

    private JoinType joinType;

    private String joinTableName;

    private Class<S> joinTableHelperClass;

    private OnJoinCallback<TO, SO> onJoinCallback;

    public SqlJoinBean(TO mainOnHelper, JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super(joinTableAlias);
        this.mainOnHelper = mainOnHelper;
        this.joinType = joinType;
        this.joinTableName = joinTableName;
        this.joinTableHelperClass = joinTableHelperClass;
        this.onJoinCallback = onJoinCallback;
    }

    @Override
    public JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions) {
        return CallbackEngineExecutor.execute(this.joinType, this.mainOnHelper, this.joinTableName, this.joinTableHelperClass, this.tableAlias, this.onJoinCallback, sqlBuilderOptions);
    }
}