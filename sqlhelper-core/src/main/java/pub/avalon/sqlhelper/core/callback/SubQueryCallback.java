package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SubQueryCallback {

    SelectSqlBuilderResult apply();
}