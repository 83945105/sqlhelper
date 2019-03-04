package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 条件模组
 * 用于记录条件信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereModel<T extends Model<T, TL, TO, TW, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
        TO extends OnModel<T, TL, TO, TW, TS, TG>,
        TW extends WhereModel<T, TL, TO, TW, TS, TG>,
        TS extends SortModel<T, TL, TO, TW, TS, TG>,
        TG extends GroupModel<T, TL, TO, TW, TS, TG>> {

    private SqlData<T> sqlData;

    protected WhereBuilder<T, TL, TO, TW, TS, TG> whereBuilder = new WhereBuilder<>((TW) this);

    public SqlData<T> getSqlData() {
        return sqlData;
    }

    public void setSqlData(SqlData<T> sqlData) {
        this.sqlData = sqlData;
    }

    public WhereBuilder<T, TL, TO, TW, TS, TG> getWhereBuilder() {
        return whereBuilder;
    }

}
