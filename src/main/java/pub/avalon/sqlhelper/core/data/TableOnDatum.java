package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.List;

/**
 * on数据
 *
 * @author 白超
 * @date 2019/6/13
 */
public class TableOnDatum<T extends TableHelper> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private List<OnDataLinker> onDataLinkers;

    public TableOnDatum(Class<T> tableHelperClass, String tableAlias, List<OnDataLinker> onDataLinkers) {
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        this.onDataLinkers = onDataLinkers;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<OnDataLinker> getOnDataLinkers() {
        return onDataLinkers;
    }

}
