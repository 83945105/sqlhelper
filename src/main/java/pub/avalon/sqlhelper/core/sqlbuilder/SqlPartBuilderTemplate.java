package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.SqlDataConsumer;

/**
 * Sql片段构建器模板
 *
 * @author 白超
 * @date 2019/7/13
 */
public interface SqlPartBuilderTemplate {

    SqlBuilderResult buildColumn(SqlDataConsumer sqlDataConsumer);

    SqlBuilderResult buildJoin(SqlDataConsumer sqlDataConsumer);

    SqlBuilderResult buildWhere(SqlDataConsumer sqlDataConsumer);

    SqlBuilderResult buildGroup(SqlDataConsumer sqlDataConsumer);

    SqlBuilderResult buildSort(SqlDataConsumer sqlDataConsumer);

    SqlBuilderResult buildLimit(SqlDataConsumer sqlDataConsumer);

}
