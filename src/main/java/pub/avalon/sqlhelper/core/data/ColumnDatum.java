package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;

/**
 * 列数据
 *
 * @author 白超
 * @date 2019/3/4
 */
public final class ColumnDatum implements SqlPartDatum {

    private String templateTableName;

    private String templateTableAlias;

    private String templateColumnName;

    private String templateColumnAlias;

    private String mappingFieldName;

    private ColumnHandler[] columnHandlers;

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.templateTableName = templateTableName;
        this.templateTableAlias = templateTableAlias;
        this.templateColumnName = templateColumnName;
        this.templateColumnAlias = templateColumnAlias;
        this.mappingFieldName = mappingFieldName;
        this.tableName = templateTableName;
        this.tableAlias = templateTableAlias;
        this.columnName = templateColumnName;
        this.columnAlias = templateColumnAlias;
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler[] columnHandlers) {
        this.templateTableName = templateTableName;
        this.templateTableAlias = templateTableAlias;
        this.templateColumnName = templateColumnName;
        this.templateColumnAlias = templateColumnAlias;
        this.mappingFieldName = mappingFieldName;
        this.columnHandlers = columnHandlers;
        this.tableName = templateTableName;
        this.tableAlias = templateTableAlias;
        this.columnName = templateColumnName;
        this.columnAlias = templateColumnAlias;
    }

    public ColumnDatum setTemplateTableName(String templateTableName) {
        this.templateTableName = templateTableName;
        return this;
    }

    public ColumnDatum setTemplateTableAlias(String templateTableAlias) {
        this.templateTableAlias = templateTableAlias;
        return this;
    }

    public ColumnDatum setTemplateColumnName(String templateColumnName) {
        this.templateColumnName = templateColumnName;
        return this;
    }

    public ColumnDatum setTemplateColumnAlias(String templateColumnAlias) {
        this.templateColumnAlias = templateColumnAlias;
        return this;
    }

    public ColumnDatum setMappingFieldName(String mappingFieldName) {
        this.mappingFieldName = mappingFieldName;
        return this;
    }

    public ColumnDatum setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public ColumnDatum setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public ColumnDatum setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public ColumnDatum setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
        return this;
    }

    public String getTemplateTableName() {
        return templateTableName;
    }

    public String getTemplateTableAlias() {
        return templateTableAlias;
    }

    public String getTemplateColumnName() {
        return templateColumnName;
    }

    public String getTemplateColumnAlias() {
        return templateColumnAlias;
    }

    public String getMappingFieldName() {
        return mappingFieldName;
    }

    public ColumnHandler[] getColumnHandlers() {
        return columnHandlers;
    }

    public void setColumnHandlers(ColumnHandler[] columnHandlers) {
        this.columnHandlers = columnHandlers;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

}
