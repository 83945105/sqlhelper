package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitSql;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface LimitEngine<R extends LimitEngine<R>> {

    R limitTop(Long num);

    R limitOne();

    R limit(LimitSql limit);

    R limit(Long total, Long currentPage, Long pageSize);

}
