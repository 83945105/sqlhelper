package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.helper.HavingHelper;

/**
 * @author 白超
 * @date 2019/7/18
 */
public abstract class SqlHaving<TH extends HavingHelper<TH>> {

    private String tableAlias;

    public SqlHaving() {
        this.tableAlias = BeanUtils.getHavingHelper(this).getTableAlias();
    }

    public SqlHaving(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
