package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.JoinCallback;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.engine.JoinEngine;
import pub.avalon.sqlhelper.core.engine.builder.beans.AbstractSqlJoinBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlJoinBean;
import pub.avalon.sqlhelper.core.engine.callback.JoinCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class SqlJoin<TO extends OnHelper<TO>> implements JoinEngine<SqlJoin<TO>>, JoinCallbackEngine<TO, SqlJoin<TO>> {

    private TO onHelper;
    private String tableAlias;

    {
        this.onHelper = HelperManager.findJoinHelperClassFromAncestorsGenericType(this);
    }

    public SqlJoin() {
        this.tableAlias = this.onHelper.getTableAlias();
    }

    public SqlJoin(String tableAlias) {
        this.onHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlJoinBean> sqlJoinBeans = new ArrayList<>(1);

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlJoin<TO> join(JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, JoinCallback<TO, SO> joinCallback) {
        this.sqlJoinBeans.add(new SqlJoinBean<>(this.onHelper, joinType, joinTableName, joinTableHelperClass, joinTableAlias, joinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlJoinBean> getSqlJoinBeans() {
        return sqlJoinBeans;
    }

    public List<JoinTableDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return execute(this, sqlBuilderOptions);
    }

    public static <FO extends OnHelper<FO>> List<JoinTableDatum> execute(SqlJoin<FO> sqlJoin, SqlBuilderOptions sqlBuilderOptions) {
        return sqlJoin.getSqlJoinBeans().stream().map(sqlJoinBean -> sqlJoinBean.execute(sqlBuilderOptions)).collect(Collectors.toList());
    }
}