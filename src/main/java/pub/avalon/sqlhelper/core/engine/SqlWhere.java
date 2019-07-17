package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlWhere<TW extends WhereHelper<TW>> implements WhereEngine<TW, SqlWhere<TW>> {

    private String tableAlias;

    public SqlWhere() {
        this.tableAlias = BeanUtils.getWhereHelper(this).getTableAlias();
    }

    public SqlWhere(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public SqlWhere<TW> where(WhereCallback<TW> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlWhere<TW> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> callback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
