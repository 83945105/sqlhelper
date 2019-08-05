package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author 白超
 * @date 2019/7/25
 */
public final class SubQueryColumnDatum {

    private String columnAlias;

    private SqlBuilderResult sqlBuilderResult;

    public SubQueryColumnDatum(String columnAlias, SqlBuilderResult sqlBuilderResult) {
        this.columnAlias = columnAlias;
        this.sqlBuilderResult = sqlBuilderResult;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public SubQueryColumnDatum setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
        return this;
    }

    public SqlBuilderResult getSqlBuilderResult() {
        return sqlBuilderResult;
    }

    public SubQueryColumnDatum setSqlBuilderResult(SqlBuilderResult sqlBuilderResult) {
        this.sqlBuilderResult = sqlBuilderResult;
        return this;
    }
}
