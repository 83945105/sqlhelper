package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.List;

/**
 * 表条件数据
 *
 * @author 白超
 * @date 2019/6/13
 */
public final class TableWhereDatum<T extends TableHelper> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private List<WhereDataLinker> whereDataLinkers;

    public TableWhereDatum(Class<T> tableHelperClass, String tableAlias, List<WhereDataLinker> whereDataLinkers) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.whereDataLinkers = whereDataLinkers;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<WhereDataLinker> getWhereDataLinkers() {
        return whereDataLinkers;
    }

}
