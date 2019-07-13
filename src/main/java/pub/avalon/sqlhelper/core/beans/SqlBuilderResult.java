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

    private final static StringBuilder EMPTY_SQL_CONTAINER = new StringBuilder(0);

    /**
     * 空结果
     */
    public final static SqlBuilderResult NONE = new SqlBuilderResult(EMPTY_SQL_CONTAINER, Collections.emptyList());

    /**
     * Sql容器
     */
    private StringBuilder sqlContainer;

    /**
     * 参数容器
     */
    private List<Object> argsContainer;

    private SqlBuilderResult(StringBuilder sqlContainer, List<Object> argsContainer) {
        this.sqlContainer = sqlContainer;
        this.argsContainer = argsContainer;
    }

    public static SqlBuilderResult init(StringBuilder sqlContainer, List<Object> argsContainer) {
        return new SqlBuilderResult(sqlContainer, argsContainer);
    }

    public static SqlBuilderResult newInstance(String preparedStatementSql) {
        return new SqlBuilderResult(new StringBuilder(preparedStatementSql), Collections.emptyList());
    }

    public static SqlBuilderResult newInstance(List<Object> preparedStatementArgs) {
        return new SqlBuilderResult(EMPTY_SQL_CONTAINER, preparedStatementArgs);
    }

    public static SqlBuilderResult newInstance(String preparedStatementSql, List<Object> preparedStatementArgs) {
        return new SqlBuilderResult(new StringBuilder(preparedStatementSql), preparedStatementArgs);
    }

    public SqlBuilderResult append(SqlBuilderResult sqlBuilderResult) {
        this.sqlContainer.append(sqlBuilderResult.sqlContainer);
        this.argsContainer.addAll(sqlBuilderResult.argsContainer);
        return this;
    }

    public SqlBuilderResult appendSqlPart(String sqlPart) {
        this.sqlContainer.append(sqlPart);
        return this;
    }

    public SqlBuilderResult appendArg(Object arg) {
        this.argsContainer.add(arg);
        return this;
    }

    public SqlBuilderResult appendArgs(List<Object> args) {
        this.argsContainer.addAll(args);
        return this;
    }

    public boolean hasPreparedStatementSql() {
        return this.sqlContainer != null && this.sqlContainer.length() > 0;
    }

    public boolean hasPreparedStatementArgs() {
        return this.argsContainer != null && this.argsContainer.size() > 0;
    }

    public String getPreparedStatementSql() {
        return this.sqlContainer.toString();
    }

    public List<Object> getPreparedStatementArgs() {
        return this.argsContainer;
    }

}
