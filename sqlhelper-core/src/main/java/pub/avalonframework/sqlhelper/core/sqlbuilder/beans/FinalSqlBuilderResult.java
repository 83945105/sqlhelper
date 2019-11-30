package pub.avalonframework.sqlhelper.core.sqlbuilder.beans;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class FinalSqlBuilderResult implements TableSqlBuilderResult, SelectSqlBuilderResult, InsertSqlBuilderResult, UpdateSqlBuilderResult, DeleteSqlBuilderResult {

    private final static StringBuilder EMPTY_SQL_CONTAINER = new StringBuilder(0);

    public final static FinalSqlBuilderResult NONE = new FinalSqlBuilderResult(EMPTY_SQL_CONTAINER, Collections.emptyList());

    private StringBuilder sqlContainer;

    private List<Object> argsContainer;

    private FinalSqlBuilderResult(StringBuilder sqlContainer, List<Object> argsContainer) {
        this.sqlContainer = sqlContainer;
        this.argsContainer = argsContainer;
    }

    public static FinalSqlBuilderResult init(StringBuilder sqlContainer, List<Object> argsContainer) {
        return new FinalSqlBuilderResult(sqlContainer, argsContainer);
    }

    public static FinalSqlBuilderResult newInstance(String preparedStatementSql) {
        return new FinalSqlBuilderResult(new StringBuilder(preparedStatementSql), Collections.emptyList());
    }

    public static FinalSqlBuilderResult newInstance(List<Object> preparedStatementArgs) {
        return new FinalSqlBuilderResult(EMPTY_SQL_CONTAINER, preparedStatementArgs);
    }

    public static FinalSqlBuilderResult newInstance(String preparedStatementSql, List<Object> preparedStatementArgs) {
        return new FinalSqlBuilderResult(new StringBuilder(preparedStatementSql), preparedStatementArgs);
    }

    public FinalSqlBuilderResult append(SqlBuilderResult sqlBuilderResult) {
        this.sqlContainer.append(sqlBuilderResult.getPreparedStatementSql());
        this.argsContainer.addAll(sqlBuilderResult.getPreparedStatementArgs());
        return this;
    }

    public FinalSqlBuilderResult appendSqlPart(String sqlPart) {
        this.sqlContainer.append(sqlPart);
        return this;
    }

    public FinalSqlBuilderResult appendArg(Object arg) {
        this.argsContainer.add(arg);
        return this;
    }

    public FinalSqlBuilderResult appendArgs(List<Object> args) {
        this.argsContainer.addAll(args);
        return this;
    }

    @Override
    public boolean hasPreparedStatementSql() {
        return this.sqlContainer != null && this.sqlContainer.length() > 0;
    }

    @Override
    public boolean hasPreparedStatementArgs() {
        return this.argsContainer != null && this.argsContainer.size() > 0;
    }

    @Override
    public String getPreparedStatementSql() {
        return this.sqlContainer.toString();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        return this.argsContainer;
    }
}