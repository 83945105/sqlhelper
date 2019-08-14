package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.sqlbuilder.SelectSqlBuilder;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SubQueryCallback {

    SelectSqlBuilder apply();
}
