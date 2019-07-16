package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/16
 */
public class SqlColumnEngine<TC extends ColumnHelper<TC>> implements ColumnEngine<TC, SqlColumnEngine<TC>> {

    @Override
    public SqlColumnEngine<TC> column(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public SqlColumnEngine<TC> column(ColumnCallback<TC> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>, SO extends OnHelper<SO>, SC extends ColumnHelper<SC>, SW extends WhereHelper<SW>, SG extends GroupHelper<SG>, SS extends SortHelper<SS>> SqlColumnEngine<TC> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback) {
        return null;
    }

    @Override
    public SqlColumnEngine<TC> virtualColumn(Object value, String alias) {
        return null;
    }

    @Override
    public SqlColumnEngine<TC> groupColumn(GroupType groupType, ColumnCallback<TC> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlColumnEngine<TC> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlColumnEngine<TC> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return null;
    }
}
