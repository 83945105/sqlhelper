package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * On模组
 * 用于记录连接条件信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class OnModel<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    private SqlData<M> sqlData;

    protected OnBuilder<M, ML, MO, MC, MS, MG> onBuilder = new OnBuilder<>((MO) this);

    public SqlData<M> getSqlData() {
        return sqlData;
    }

    public void setSqlData(SqlData<M> sqlData) {
        this.sqlData = sqlData;
    }

    public OnBuilder<M, ML, MO, MC, MS, MG> getOnBuilder() {
        return onBuilder;
    }

}
