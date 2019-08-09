package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.JoinCallback;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author 白超
 * @date 2019/7/17
 */
public final class SqlJoinBean<TJ extends JoinHelper<TJ>,
        S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
        SJ extends JoinHelper<SJ>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractSqlJoinBean {

    private TJ mainJoinHelper;

    private JoinType joinType;

    private String joinTableName;

    private Class<S> joinTableHelperClass;

    private JoinCallback<TJ, SJ> joinCallback;

    public SqlJoinBean(TJ mainJoinHelper, JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, JoinCallback<TJ, SJ> joinCallback) {
        super(joinTableAlias);
        this.mainJoinHelper = mainJoinHelper;
        this.joinType = joinType;
        this.joinTableName = joinTableName;
        this.joinTableHelperClass = joinTableHelperClass;
        this.joinCallback = joinCallback;
    }

    @Override
    public JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions) {
        if (this.joinCallback != null) {
            return JoinCallback.execute(this.joinType, this.mainJoinHelper, this.joinTableName, this.joinTableHelperClass, this.tableAlias, this.joinCallback, sqlBuilderOptions);
        }
        return new JoinTableDatum(this.joinType, this.joinTableHelperClass, this.joinTableName, this.tableAlias);
    }

}
