package pub.avalonframework.sqlhelper.core.sqlbuilder.beans;

import java.util.List;

/**
 * @author baichao
 */
public interface SqlBuilderResult {

    /**
     * has prepared statement sql
     *
     * @return true or false
     */
    boolean hasPreparedStatementSql();

    /**
     * has prepared statement args
     *
     * @return true or false
     */
    boolean hasPreparedStatementArgs();

    /**
     * get prepared statement sql
     *
     * @return prepared statement sql
     */
    String getPreparedStatementSql();

    /**
     * get prepared statement args
     *
     * @return prepared statement args
     */
    List<Object> getPreparedStatementArgs();
}