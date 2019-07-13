package pub.avalon.sqlhelper.core.beans;

import java.util.Collections;
import java.util.List;

/**
 * Sql构建结果
 *
 * @author 白超
 * @date 2019/5/25
 */
public final class SqlBuilderResult {

    private final static String EMPTY_STRING = "";

    /**
     * 空结果
     */
    public final static SqlBuilderResult NONE = new SqlBuilderResult(EMPTY_STRING, Collections.emptyList());

    public static SqlBuilderResult newInstance(String preparedStatementSql) {
        return new SqlBuilderResult(preparedStatementSql, Collections.emptyList());
    }

    public static SqlBuilderResult newInstance(List<Object> preparedStatementArgs) {
        return new SqlBuilderResult(EMPTY_STRING, preparedStatementArgs);
    }

    public static SqlBuilderResult newInstance(String preparedStatementSql, List<Object> preparedStatementArgs) {
        return new SqlBuilderResult(preparedStatementSql, preparedStatementArgs);
    }

    /**
     * 预编译sql
     */
    private String preparedStatementSql;

    /**
     * 预编译参数
     */
    private List<Object> preparedStatementArgs;

    private SqlBuilderResult(String preparedStatementSql, List<Object> preparedStatementArgs) {
        this.preparedStatementSql = preparedStatementSql;
        this.preparedStatementArgs = preparedStatementArgs;
    }

    public String getPreparedStatementSql() {
        return preparedStatementSql;
    }

    public List<Object> getPreparedStatementArgs() {
        return preparedStatementArgs;
    }

}
