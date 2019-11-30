package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SubQueryCallback {

    SelectSqlBuilderResult apply();
}