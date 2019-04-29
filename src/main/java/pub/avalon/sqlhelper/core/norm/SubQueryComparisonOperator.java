package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * 子查询比较运算符
 *
 * @author 白超
 * @date 2019/4/29
 */
public interface SubQueryComparisonOperator<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    /**
     * 等于
     *
     * @param columnModel 条件构建器
     * @return Where条件模组
     */
    MW equalTo(ColumnModel columnModel);

    /**
     * 不等于
     *
     * @param columnModel Where条件构建器
     * @return Where条件模组
     */
    MW notEqualTo(ColumnModel columnModel);

    /**
     * 大于
     *
     * @param columnModel Where条件构建器
     * @return Where条件模组
     */
    MW greaterThan(ColumnModel columnModel);

    /**
     * 大于等于
     *
     * @param columnModel Where条件构建器
     * @return Where条件模组
     */
    MW greaterThanAndEqualTo(ColumnModel columnModel);

    /**
     * 小于
     *
     * @param columnModel Where条件构建器
     * @return Where条件模组
     */
    MW lessThan(ColumnModel columnModel);

    /**
     * 小于等于
     *
     * @param columnModel Where条件构建器
     * @return Where条件模组
     */
    MW lessThanAndEqualTo(ColumnModel columnModel);

}
