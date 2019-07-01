package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.helper.TableHelper;

/**
 * 连接表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class JoinTableDatum extends AbstractTableDatum {

    private JoinType joinType;

    public JoinTableDatum(JoinType joinType, Class<?> tableHelperClass, TableHelper tableHelper) {
        super(tableHelperClass, tableHelper);
        this.joinType = joinType;
    }

    public JoinTableDatum(JoinType joinType, Class<?> tableHelperClass, TableHelper tableHelper, String tableName, String tableAlias) {
        super(tableHelperClass, tableHelper);
        this.joinType = joinType;
        this.setTableName(tableName);
        this.setTableAlias(tableAlias);
    }

    private TableOnDatum tableOnDatum;

    public JoinType getJoinType() {
        return joinType;
    }

    public TableOnDatum getTableOnDatum() {
        return tableOnDatum;
    }

    public void setTableOnDatum(TableOnDatum tableOnDatum) {
        this.tableOnDatum = tableOnDatum;
    }

}
