package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableOnDatum {

    private String tableAlias;

    private List<OnDataLinker> onDataLinkers;

    public TableOnDatum(String tableAlias, List<OnDataLinker> onDataLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.onDataLinkers = onDataLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<OnDataLinker> getOnDataLinkers() {
        return onDataLinkers;
    }
}