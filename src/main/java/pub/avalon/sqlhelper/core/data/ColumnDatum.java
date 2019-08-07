package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.ColumnType;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * 列数据
 *
 * @author 白超
 * @date 2019/3/4
 */
public final class ColumnDatum extends AbstractSqlPartDatum<ColumnDatum> {

    private ColumnType columnType = ColumnType.DEFAULT;

    private ColumnHandler[] columnHandlers;

    private String sqlPart;

    private Object columnValue;

    private SqlBuilderResult sqlBuilderResult;

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, Object columnValue, String mappingFieldName) {
        super(templateTableName, templateTableAlias, null, null, mappingFieldName);
        this.columnValue = columnValue;
        this.columnType = ColumnType.VIRTUAL;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, SqlBuilderResult sqlBuilderResult, String mappingFieldName) {
        super(templateTableName, templateTableAlias, null, null, mappingFieldName);
        this.sqlBuilderResult = sqlBuilderResult;
        this.columnType = ColumnType.SUB_QUERY;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, ColumnHandler... columnHandlers) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        this.columnHandlers = columnHandlers;
        this.columnType = ColumnType.HANDLER;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        this.columnHandlers = columnHandlers;
        this.columnType = ColumnType.HANDLER;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.columnType = ColumnType.SQL_PART;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public ColumnHandler[] getColumnHandlers() {
        return columnHandlers;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public SqlBuilderResult getSqlBuilderResult() {
        return sqlBuilderResult;
    }

    public ColumnDatum setColumnType(ColumnType columnType) {
        this.columnType = columnType;
        return this;
    }

    public ColumnDatum setColumnHandlers(ColumnHandler... columnHandlers) {
        this.columnHandlers = columnHandlers;
        this.setColumnType(ColumnType.HANDLER);
        return this;
    }

    public ColumnDatum setVirtualColumn(Object columnValue, String columnAlias) {
        this.columnValue = columnValue;
        this.setColumnAlias(columnAlias);
        this.setColumnType(ColumnType.VIRTUAL);
        return this;
    }

    public ColumnDatum setSubQueryColumn(SqlBuilderResult sqlBuilderResult, String columnAlias) {
        this.sqlBuilderResult = sqlBuilderResult;
        this.setColumnAlias(columnAlias);
        this.setColumnType(ColumnType.SUB_QUERY);
        return this;
    }

}
