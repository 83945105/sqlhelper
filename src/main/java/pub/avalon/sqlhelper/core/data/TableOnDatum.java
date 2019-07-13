package pub.avalon.sqlhelper.core.data;

import java.util.List;

/**
 * on数据
 *
 * @author 白超
 * @date 2019/6/13
 */
public final class TableOnDatum {

    private String tableAlias;

    private List<OnDataLinker> onDataLinkers;

    public TableOnDatum(String tableAlias, List<OnDataLinker> onDataLinkers) {
        if (tableAlias == null) {
            throw new RuntimeException("tableAlias can not be null.");
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
