package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.engine.WhereEngine;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlWhereBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlWhereBeanJoin;
import pub.avalon.sqlhelper.core.engine.callback.WhereCallbackEngine;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlWhere<TW extends WhereHelper<TW>> implements WhereEngine<SqlWhere<TW>>, WhereCallbackEngine<TW, SqlWhere<TW>> {

    private TW whereHelper;
    private String tableAlias;

    {
        this.whereHelper = BeanUtils.getWhereHelper(this);
    }

    public SqlWhere() {
        this.tableAlias = this.whereHelper.getTableAlias();
    }

    public SqlWhere(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    private List<SqlWhereBean<TW>> sqlWhereBeans = new ArrayList<>(1);

    @Override
    public SqlWhere<TW> where(WhereHelper<?>... whereHelpers) {
        this.sqlWhereBeans.add(new SqlWhereBean<>(this.whereHelper).setWhereHelpers(whereHelpers));
        return this;
    }

    @Override
    public SqlWhere<TW> where(WhereCallback<TW> whereCallback) {
        this.sqlWhereBeans.add(new SqlWhereBean<>(this.whereHelper).setWhereCallback(whereCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlWhere<TW> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        this.sqlWhereBeans.add(new SqlWhereBeanJoin<S, SJ, SC, SW, SG, SH, SS, TW>(this.whereHelper)
                .setTableHelperClass(tableHelperClass)
                .setTableAlias(tableAlias)
                .setWhereJoinCallback(whereJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SqlWhereBean<TW>> getSqlWhereBeans() {
        return sqlWhereBeans;
    }
}
