package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.engine.QueryEngine;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
public interface SubQuery<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>,
        T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> {

    /**
     * 子查询处理
     *
     * @param mainTable
     * @param query
     * @return
     */
    SqlBuilder apply(MW mainTable, QueryEngine<T, TC, TO, TW, TS, TG> query);

}
