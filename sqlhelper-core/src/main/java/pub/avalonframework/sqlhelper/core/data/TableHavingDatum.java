package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableHavingDatum {

    private String tableAlias;

    private List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers;

    public TableHavingDatum(String tableAlias, List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.comparisonSqlPartDataLinkers = comparisonSqlPartDataLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ComparisonSqlPartDataLinker> getComparisonSqlPartDataLinkers() {
        return comparisonSqlPartDataLinkers;
    }
}
