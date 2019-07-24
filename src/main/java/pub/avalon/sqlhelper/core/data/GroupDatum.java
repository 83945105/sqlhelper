package pub.avalon.sqlhelper.core.data;

/**
 * 分组数据
 *
 * @author 白超
 * @date 2019/5/6
 */
public final class GroupDatum implements SqlPartDatum {

    private String templateTableName;

    private String templateTableAlias;

    private String templateColumnName;

    private String templateColumnAlias;

    private String mappingFieldName;

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    public GroupDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
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

    public GroupDatum setTemplateTableName(String templateTableName) {
        this.templateTableName = templateTableName;
        return this;
    }

    public GroupDatum setTemplateTableAlias(String templateTableAlias) {
        this.templateTableAlias = templateTableAlias;
        return this;
    }

    public GroupDatum setTemplateColumnName(String templateColumnName) {
        this.templateColumnName = templateColumnName;
        return this;
    }

    public GroupDatum setTemplateColumnAlias(String templateColumnAlias) {
        this.templateColumnAlias = templateColumnAlias;
        return this;
    }

    public GroupDatum setMappingFieldName(String mappingFieldName) {
        this.mappingFieldName = mappingFieldName;
        return this;
    }

    public GroupDatum setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public GroupDatum setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public GroupDatum setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public GroupDatum setColumnAlias(String columnAlias) {
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
