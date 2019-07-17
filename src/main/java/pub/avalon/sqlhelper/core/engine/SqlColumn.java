package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.SqlColumnBean;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/16
 */
public abstract class SqlColumn<TC extends ColumnHelper<TC>> implements ColumnEngine<TC, SqlColumn<TC>> {

    private List<SqlColumnBean<TC>> sqlColumnBeans = new ArrayList<>(1);

    @Override
    public SqlColumn<TC> column(ColumnHelper<?>... columnHelpers) {
        this.sqlColumnBeans.add(new SqlColumnBean<TC>().setColumnHelpers(columnHelpers));
        return this;
    }

    @Override
    public SqlColumn<TC> column(ColumnCallback<TC> columnCallback) {
        this.sqlColumnBeans.add(new SqlColumnBean<TC>().setColumnCallback(columnCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlColumn<TC> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        return this;
    }

    @Override
    public SqlColumn<TC> virtualColumn(Object value, String alias) {
        return null;
    }

    @Override
    public SqlColumn<TC> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlColumn<TC> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlColumn<TC> subQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return null;
    }

    public List<SqlColumnBean<TC>> getSqlColumnBeans() {
        return sqlColumnBeans;
    }
}
