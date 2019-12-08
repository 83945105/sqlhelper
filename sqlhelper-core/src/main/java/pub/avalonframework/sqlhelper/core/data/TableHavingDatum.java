package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableHavingDatum {

    private String tableAlias;

    private List<HavingDataLinker> havingDataLinkers;

    public TableHavingDatum(String tableAlias, List<HavingDataLinker> havingDataLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.havingDataLinkers = havingDataLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<HavingDataLinker> getHavingDataLinkers() {
        return havingDataLinkers;
    }
}
