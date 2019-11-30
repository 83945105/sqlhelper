package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

/**
 * @author baichao
 */
public final class ColumnDatum extends AbstractSqlPartDatum<ColumnDatum> {

    private Type type = Type.DEFAULT;

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
        this.type = Type.VIRTUAL;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, SqlBuilderResult sqlBuilderResult, String mappingFieldName) {
        super(templateTableName, templateTableAlias, null, null, mappingFieldName);
        this.sqlBuilderResult = sqlBuilderResult;
        this.type = Type.SUB_QUERY;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, ColumnHandler... columnHandlers) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        this.columnHandlers = columnHandlers;
        this.type = Type.HANDLER;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        this.columnHandlers = columnHandlers;
        this.type = Type.HANDLER;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.type = Type.SQL_PART;
    }

    public Type getType() {
        return type;
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

    public ColumnDatum setType(Type type) {
        this.type = type;
        return this;
    }

    public ColumnDatum setColumnHandlers(ColumnHandler... columnHandlers) {
        this.columnHandlers = columnHandlers;
        this.setType(Type.HANDLER);
        return this;
    }

    public ColumnDatum setVirtualColumn(Object columnValue, String columnAlias) {
        this.columnValue = columnValue;
        this.setColumnAlias(columnAlias);
        this.setType(Type.VIRTUAL);
        return this;
    }

    public ColumnDatum setSubQueryColumn(SqlBuilderResult sqlBuilderResult, String columnAlias) {
        this.sqlBuilderResult = sqlBuilderResult;
        this.setColumnAlias(columnAlias);
        this.setType(Type.SUB_QUERY);
        return this;
    }

    public enum Type {
        /**
         * default type
         */
        DEFAULT,
        /**
         * virtual column
         */
        VIRTUAL,
        /**
         * sub query column
         */
        SUB_QUERY,
        /**
         * column handler {@link ColumnHandler}
         */
        HANDLER,
        /**
         * custom column sql
         */
        SQL_PART
    }

    @Override
    public ColumnDatum setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public ColumnDatum setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public ColumnDatum setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public ColumnDatum setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }
}