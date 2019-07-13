package pub.avalon.sqlhelper.core.data;

import java.util.List;

/**
 * 表条件数据
 *
 * @author 白超
 * @date 2019/6/13
 */
public final class TableWhereDatum {

    private String tableAlias;

    private List<WhereDataLinker> whereDataLinkers;

    public TableWhereDatum(String tableAlias, List<WhereDataLinker> whereDataLinkers) {
        if (tableAlias == null) {
            throw new RuntimeException("tableAlias can not be null.");
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
