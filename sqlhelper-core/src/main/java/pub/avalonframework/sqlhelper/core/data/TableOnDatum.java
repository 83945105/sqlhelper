package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class TableOnDatum {

    private String tableAlias;

    private List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers;

    public TableOnDatum(String tableAlias, List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers) {
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

    public void merge(TableOnDatum tableOnDatum) {
        if (tableOnDatum == null) {
            return;
        }
        if (!this.getTableAlias().equals(tableOnDatum.getTableAlias())) {
            ExceptionUtils.inconsistentAliasException();
        }
        this.addAllComparisonSqlPartDataLinkers(tableOnDatum.getComparisonSqlPartDataLinkers());
    }

    public void addAllComparisonSqlPartDataLinkers(List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers) {
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return;
        }
        if (this.comparisonSqlPartDataLinkers == null) {
            this.comparisonSqlPartDataLinkers = new ArrayList<>(comparisonSqlPartDataLinkers.size());
        }
        this.comparisonSqlPartDataLinkers.addAll(comparisonSqlPartDataLinkers);
    }
}