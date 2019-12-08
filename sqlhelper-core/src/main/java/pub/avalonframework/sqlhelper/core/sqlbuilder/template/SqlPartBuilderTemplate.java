package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.data.SqlDataConsumer;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author baichao
 */
public interface SqlPartBuilderTemplate {

    /**
     * build select column sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildSelectColumn(SqlDataConsumer sqlDataConsumer);

    /**
     * build join sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildJoin(SqlDataConsumer sqlDataConsumer);

    /**
     * build where sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildWhere(SqlDataConsumer sqlDataConsumer);

    /**
     * build group sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildGroup(SqlDataConsumer sqlDataConsumer);

    /**
     * build having sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}er
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildHaving(SqlDataConsumer sqlDataConsumer);

    /**
     * build sort sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildSort(SqlDataConsumer sqlDataConsumer);

    /**
     * build limit sql part result
     *
     * @param sqlDataConsumer {@link SqlDataConsumer}
     * @return {@link SqlBuilderResult}
     */
    SqlBuilderResult buildLimit(SqlDataConsumer sqlDataConsumer);
}