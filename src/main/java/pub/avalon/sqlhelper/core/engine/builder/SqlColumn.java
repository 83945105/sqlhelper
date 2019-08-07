package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.SqlColumnBean;
import pub.avalon.sqlhelper.core.beans.SqlColumnBeanJoin;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalon.sqlhelper.core.engine.ColumnEngine;
import pub.avalon.sqlhelper.core.engine.callback.ColumnCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/16
 */
public abstract class SqlColumn<TC extends ColumnHelper<TC>> implements ColumnEngine<SqlColumn<TC>>, ColumnCallbackEngine<TC, SqlColumn<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = BeanUtils.getColumnHelper(this);
    }

    public SqlColumn() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public SqlColumn(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    private List<SqlColumnBean<TC>> sqlColumnBeans = new ArrayList<>(1);

    @Override
    public SqlColumn<TC> column(ColumnHelper<?>... columnHelpers) {
        this.sqlColumnBeans.add(new SqlColumnBean<>(this.columnHelper).setColumnHelpers(columnHelpers));
        return this;
    }

    @Override
    public SqlColumn<TC> column(ColumnCallback<TC> columnCallback) {
        this.sqlColumnBeans.add(new SqlColumnBean<>(this.columnHelper).setColumnCallback(columnCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlColumn<TC> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.sqlColumnBeans.add(new SqlColumnBeanJoin<S, SJ, SC, SW, SG, SH, SS, TC>(this.columnHelper)
                .setTableHelperClass(tableHelperClass)
                .setTableAlias(tableAlias)
                .setColumnCallbackJoin(columnCallback));
        return this;
    }

    @Override
    public SqlColumn<TC> virtualColumn(Object columnValue, String columnAlias) {
        return null;
    }

    @Override
    public SqlColumn<TC> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlColumn<TC> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return null;
    }

    @Override
    public SqlColumn<TC> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SqlColumnBean<TC>> getSqlColumnBeans() {
        return sqlColumnBeans;
    }
}
