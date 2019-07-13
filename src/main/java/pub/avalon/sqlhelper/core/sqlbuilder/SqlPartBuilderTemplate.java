package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.data.SqlDataConsumer;

/**
 * Sql片段构建器模板
 *
 * @author 白超
 * @date 2019/7/13
 */
public interface SqlPartBuilderTemplate<T> {

    T buildColumn(SqlDataConsumer sqlDataConsumer);

    T buildJoin(SqlDataConsumer sqlDataConsumer);

    T buildWhere(SqlDataConsumer sqlDataConsumer);

    T buildGroup(SqlDataConsumer sqlDataConsumer);

    T buildSort(SqlDataConsumer sqlDataConsumer);

    T buildLimit(SqlDataConsumer sqlDataConsumer);

}
