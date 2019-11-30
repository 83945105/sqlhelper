package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableWhereDatum {

    private String tableAlias;

    private List<WhereDataLinker> whereDataLinkers;

    public TableWhereDatum(String tableAlias, List<WhereDataLinker> whereDataLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.whereDataLinkers = whereDataLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<WhereDataLinker> getWhereDataLinkers() {
        return whereDataLinkers;
    }
}