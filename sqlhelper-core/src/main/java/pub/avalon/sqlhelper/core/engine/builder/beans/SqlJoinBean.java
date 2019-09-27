package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.engine.callback.JoinCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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

    private OnCallback<TO, SO> onCallback;

    public SqlJoinBean(TO mainOnHelper, JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnCallback<TO, SO> onCallback) {
        super(joinTableAlias);
        this.mainOnHelper = mainOnHelper;
        this.joinType = joinType;
        this.joinTableName = joinTableName;
        this.joinTableHelperClass = joinTableHelperClass;
        this.onCallback = onCallback;
    }

    @Override
    public JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions) {
        return JoinCallbackEngine.execute(this.joinType, this.mainOnHelper, this.joinTableName, this.joinTableHelperClass, this.tableAlias, this.onCallback, sqlBuilderOptions);
    }
}