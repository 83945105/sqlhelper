package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/7/17
 */
public abstract class SqlGroup<TG extends GroupHelper<TG>> implements GroupEngine<TG, SqlGroup<TG>> {

    private String tableAlias;

    public SqlGroup() {
        this.tableAlias = BeanUtils.getGroupHelper(this).getTableAlias();
    }

    public SqlGroup(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public SqlGroup<TG> group(GroupHelper<?>... groupHelpers) {
        return null;
    }

    @Override
    public SqlGroup<TG> group(GroupCallback<TG> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SqlGroup<TG> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
