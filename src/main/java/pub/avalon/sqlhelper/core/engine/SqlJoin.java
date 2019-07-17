package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlJoin<TO extends OnHelper<TO>> implements JoinEngine<TO, SqlJoin<TO>> {

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlJoin<TO> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<TO, SO> callback) {
        return null;
    }

}
