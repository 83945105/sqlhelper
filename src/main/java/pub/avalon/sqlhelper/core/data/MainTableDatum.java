package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

/**
 * 主表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class MainTableDatum extends AbstractTableDatum {

    public MainTableDatum(Class<?> tableHelperClass, TableHelper tableHelper) {
        super(tableHelperClass, tableHelper);
    }

    public MainTableDatum(Class<?> tableHelperClass, TableHelper tableHelper, String tableName, String tableAlias) {
        super(tableHelperClass, tableHelper);
        this.setTableName(tableName);
        this.setTableAlias(tableAlias);
    }

}
