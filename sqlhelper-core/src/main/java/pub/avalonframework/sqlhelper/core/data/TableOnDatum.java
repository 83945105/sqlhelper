package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.ArrayList;
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

    public void merge(TableOnDatum tableOnDatum) {
        if (tableOnDatum == null) {
            return;
        }
        if (!this.getTableAlias().equals(tableOnDatum.getTableAlias())) {
            ExceptionUtils.inconsistentAliasException();
        }
        this.addAllOnDataLinkers(tableOnDatum.getOnDataLinkers());
    }

    public void addAllOnDataLinkers(List<OnDataLinker> onDataLinkers) {
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return;
        }
        if (this.onDataLinkers == null) {
            this.onDataLinkers = new ArrayList<>(onDataLinkers.size());
        }
        this.onDataLinkers.addAll(onDataLinkers);
    }
}