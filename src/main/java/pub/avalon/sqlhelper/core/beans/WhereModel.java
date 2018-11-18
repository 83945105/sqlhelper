package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 条件模组
 * 用于记录条件信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereModel<T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    protected WhereBuilder<T, TL, TO, TC, TS, TG> whereBuilder = new WhereBuilder<>((TC) this);

    public WhereBuilder<T, TL, TO, TC, TS, TG> getWhereBuilder() {
        return whereBuilder;
    }
}
