package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.JoinCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlJoin<TJ extends JoinHelper<TJ>> implements JoinEngine<TJ, SqlJoin<TJ>> {

    private String tableAlias;

    public SqlJoin() {
        this.tableAlias = BeanUtils.getJoinHelper(this).getTableAlias();
    }

    public SqlJoin(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlJoin<TJ> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, JoinCallback<TJ, SJ> joinCallback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
