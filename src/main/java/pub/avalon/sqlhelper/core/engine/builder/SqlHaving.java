package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.engine.HavingEngine;
import pub.avalon.sqlhelper.core.engine.callback.HavingCallbackEngine;
import pub.avalon.sqlhelper.core.helper.HavingHelper;

/**
 * @author 白超
 * @date 2019/7/18
 */
public abstract class SqlHaving<TH extends HavingHelper<TH>> implements HavingEngine<SqlHaving<TH>>, HavingCallbackEngine<TH, SqlHaving<TH>> {

    private TH havingHelper;
    private String tableAlias;

    {
        this.havingHelper = BeanUtils.getHavingHelper(this);
    }

    public SqlHaving() {
        this.tableAlias = BeanUtils.getHavingHelper(this).getTableAlias();
    }

    public SqlHaving(String tableAlias) {
        this.havingHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
