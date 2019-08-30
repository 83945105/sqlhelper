package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.JoinType;

/**
 * @author baichao
 */
public final class JoinTableDatum extends AbstractTableDatum {

    private JoinType joinType;

    public JoinTableDatum(JoinType joinType, Class<?> tableHelperClass, String tableName, String tableAlias) {
        super(tableHelperClass, tableName, tableAlias);
        this.joinType = joinType;
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