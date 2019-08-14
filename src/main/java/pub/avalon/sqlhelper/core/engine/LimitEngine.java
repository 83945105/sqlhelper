package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitSql;

/**
 * 分页引擎
 *
 * @author baichao
 * @since 2018/7/10
 */
public interface LimitEngine<R> extends Engine {

    R limitTop(Long num);

    R limitOne();

    R limit(LimitSql limit);

    R limit(Long total, Long currentPage, Long pageSize);

}
