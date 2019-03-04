package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * 连接查询On条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface On<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>,
        T extends Model<T, TL, TO, TW, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
        TO extends OnModel<T, TL, TO, TW, TS, TG>,
        TW extends WhereModel<T, TL, TO, TW, TS, TG>,
        TS extends SortModel<T, TL, TO, TW, TS, TG>,
        TG extends GroupModel<T, TL, TO, TW, TS, TG>> {

    /**
     * 构建On条件
     *
     * @param on        连接对象
     * @param joinTable 连接表的条件
     * @param mainTable 主表的条件
     * @return
     */
    OnLink<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> apply(OnLink<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> on, TO joinTable, MO mainTable);

}
