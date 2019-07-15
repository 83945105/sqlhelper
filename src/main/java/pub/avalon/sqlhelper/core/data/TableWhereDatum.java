package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

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
