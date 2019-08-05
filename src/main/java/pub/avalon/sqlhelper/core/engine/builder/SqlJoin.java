package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.beans.SqlJoinBean;
import pub.avalon.sqlhelper.core.beans.SqlJoinBeanFinal;
import pub.avalon.sqlhelper.core.callback.JoinCallback;
import pub.avalon.sqlhelper.core.engine.JoinEngine;
import pub.avalon.sqlhelper.core.engine.callback.JoinCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlJoin<TJ extends JoinHelper<TJ>> implements JoinEngine<SqlJoin<TJ>>, JoinCallbackEngine<TJ, SqlJoin<TJ>> {

    private TJ joinHelper;
    private String tableAlias;

    {
        this.joinHelper = BeanUtils.getJoinHelper(this);
    }

    public SqlJoin() {
        this.tableAlias = this.joinHelper.getTableAlias();
    }

    public SqlJoin(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    private List<SqlJoinBean<TJ>> sqlJoinBeans = new ArrayList<>(1);

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlJoin<TJ> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TJ, SJ> joinCallback) {
        this.sqlJoinBeans.add(new SqlJoinBeanFinal<S, SJ, SC, SW, SG, SH, SS, TJ>(this.joinHelper)
                .setJoinType(joinType)
                .setTableName(tableName)
                .setTableHelperClass(tableHelperClass)
                .setTableAlias(tableAlias)
                .setJoinCallback(joinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SqlJoinBean<TJ>> getSqlJoinBeans() {
        return sqlJoinBeans;
    }
}
