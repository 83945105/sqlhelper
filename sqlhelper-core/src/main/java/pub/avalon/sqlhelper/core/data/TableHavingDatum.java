package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableHavingDatum {

    private String tableAlias;

    private List<HavingDatum> havingData;

    public TableHavingDatum(String tableAlias, List<HavingDatum> havingData) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.havingData = havingData;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<HavingDatum> getHavingData() {
        return havingData;
    }
}
