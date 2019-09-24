package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.engine.HavingEngine;
import pub.avalon.sqlhelper.core.engine.callback.HavingCallbackEngine;
import pub.avalon.sqlhelper.core.helper.HavingHelper;
import pub.avalon.sqlhelper.core.utils.HelperManager;

/**
 * @author baichao
 */
public abstract class SqlHaving<TH extends HavingHelper<TH>> implements HavingEngine<SqlHaving<TH>>, HavingCallbackEngine<TH, SqlHaving<TH>> {

    private TH havingHelper;
    private String tableAlias;

    {
        this.havingHelper = HelperManager.findHavingHelperClassFromAncestorsGenericType(this);
    }

    public SqlHaving() {
        this.tableAlias = this.havingHelper.getTableAlias();
    }

    public SqlHaving(String tableAlias) {
        this.havingHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}