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
public final class JoinTableData<T extends TableHelper> extends AbstractTableData<T> {

    private JoinType joinType = JoinType.INNER;

    private TableOnDatum tableOnDatum;

    public JoinTableData(Class<T> tableClass) {
        super(tableClass);
    }

    public JoinTableData(Class<T> tableClass, String tableAlias) {
        super(tableClass);
        this.setTableAlias(tableAlias);
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public TableOnDatum getTableOnDatum() {
        return tableOnDatum;
    }

    public void setTableOnDatum(TableOnDatum tableOnDatum) {
        this.tableOnDatum = tableOnDatum;
    }

}
