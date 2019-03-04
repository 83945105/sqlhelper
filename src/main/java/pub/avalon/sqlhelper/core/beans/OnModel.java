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
public class OnModel<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    private SqlData<M> sqlData;

    protected OnBuilder<M, MC, MO, MW, MS, MG> onBuilder = new OnBuilder<>((MO) this);

    public SqlData<M> getSqlData() {
        return sqlData;
    }

    public void setSqlData(SqlData<M> sqlData) {
        this.sqlData = sqlData;
    }

    public OnBuilder<M, MC, MO, MW, MS, MG> getOnBuilder() {
        return onBuilder;
    }

}
