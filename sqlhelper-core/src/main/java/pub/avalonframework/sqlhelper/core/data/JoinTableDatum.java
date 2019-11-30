package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.JoinType;

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

    public JoinTableDatum appendTableOnDatum(TableOnDatum tableOnDatum) {
        if (tableOnDatum == null) {
            return this;
        }
        if (this.tableOnDatum == null) {
            this.setTableOnDatum(tableOnDatum);
            return this;
        }
        this.tableOnDatum.merge(tableOnDatum);
        return this;
    }

    public JoinTableDatum merge(JoinTableDatum joinTableDatum) {
        if (joinTableDatum == null) {
            return this;
        }
        return appendTableOnDatum(joinTableDatum.getTableOnDatum());
    }
}