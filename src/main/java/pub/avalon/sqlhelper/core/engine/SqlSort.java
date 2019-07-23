package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/17
 */
public class SqlSort<TS extends SortHelper<TS>> implements SortEngine<TS, SqlSort<TS>> {

    private String tableAlias;

    public SqlSort() {
        this.tableAlias = BeanUtils.getSortHelper(this).getTableAlias();
    }

    public SqlSort(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public SqlSort<TS> sort(SortHelper<?>... sortHelpers) {
        return null;
    }

    @Override
    public SqlSort<TS> sort(SortCallback<TS> sortCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlSort<TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
