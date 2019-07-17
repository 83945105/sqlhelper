package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlJoin<TJ extends JoinHelper<TJ>> implements JoinEngine<TJ, SqlJoin<TJ>> {

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlJoin<TJ> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TJ, SJ> callback) {
        return null;
    }

}
