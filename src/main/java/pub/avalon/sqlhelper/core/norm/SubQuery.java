package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.engine.QueryEngine;
import pub.avalon.sqlhelper.core.sql.Query;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
public interface SubQuery<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>,
        T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> {

    /**
     * 子查询处理
     *
     * @param mainTable
     * @param query
     * @return
     */
    Query apply(MC mainTable, QueryEngine<T, TL, TO, TC, TS, TG> query);

}
