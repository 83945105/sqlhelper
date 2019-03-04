package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * 条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface MainCondition<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    /**
     * 接收处理条件
     *
     * @param condition 连接条件
     * @param mainTable 主表的条件
     * @return 条件连接
     */
    WhereLink<M, MC, MO, MW, MS, MG> apply(WhereLink<M, MC, MO, MW, MS, MG> condition, MW mainTable);
}
