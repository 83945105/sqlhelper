package pub.avalon.sqlhelper.core.beans;

import java.util.List;

/**
 * Sql构建结果
 *
 * @author 白超
 * @date 2019/5/25
 */
public final class SqlBuilderResult {

    /**
     * 预编译sql
     */
    private String preparedStatementSql;

    /**
     * 预编译参数
     */
    private List<Object> preparedStatementArgs;

    public SqlBuilderResult(String preparedStatementSql, List<Object> preparedStatementArgs) {
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
