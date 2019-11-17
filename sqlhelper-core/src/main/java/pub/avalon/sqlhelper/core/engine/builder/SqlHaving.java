package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.callback.HavingCallback;
import pub.avalon.sqlhelper.core.engine.HavingEngine;
import pub.avalon.sqlhelper.core.engine.builder.beans.AbstractSqlHavingBean;
import pub.avalon.sqlhelper.core.engine.builder.beans.SqlHavingBean;
import pub.avalon.sqlhelper.core.engine.callback.HavingCallbackEngine;
import pub.avalon.sqlhelper.core.helper.HavingHelper;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;

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

    private List<AbstractSqlHavingBean> sqlHavingBeans = new ArrayList<>(1);

    @Override
    public SqlHaving<TH> having(HavingCallback<TH> havingCallback) {
        this.sqlHavingBeans.add(new SqlHavingBean<>(this.havingHelper, this.tableAlias).setHavingCallback(havingCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}