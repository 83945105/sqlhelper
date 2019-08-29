package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitSql;

/**
 * @author baichao
 */
public interface LimitEngine<R> extends Engine {

    /**
     * add limit sql data to select top num
     *
     * @param num top num
     * @return R
     */
    R limitTop(Long num);

    /**
     * add limit sql data to select first one
     *
     * @return R
     */
    R limitOne();

    /**
     * add limit sql data
     *
     * @param limit extends {@link LimitSql} object
     * @return R
     */
    R limit(LimitSql limit);

    /**
     * add limit sql data
     *
     * @param total       total num
     * @param currentPage current page num
     * @param pageSize    page size
     * @return R
     */
    R limit(Long total, Long currentPage, Long pageSize);
}