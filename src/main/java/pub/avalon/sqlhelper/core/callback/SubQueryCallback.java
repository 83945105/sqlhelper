package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.sqlbuilder.SelectSqlBuilder;

/**
 * 子查询
 *
 * @author baichao
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQueryCallback {

    SelectSqlBuilder apply();

}
