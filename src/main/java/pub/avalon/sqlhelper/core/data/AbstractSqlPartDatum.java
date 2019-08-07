package pub.avalon.sqlhelper.core.data;

/**
 * @author 白超
 * @date 2019/8/5
 */
public abstract class AbstractSqlPartDatum<R extends AbstractSqlPartDatum<R>> implements SqlPartDatum {

    private String templateTableName;

    private String templateTableAlias;

    private String templateColumnName;

    private String templateColumnAlias;

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    public AbstractSqlPartDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.templateTableName = templateTableName;
        this.templateTableAlias = templateTableAlias;
        this.templateColumnName = templateColumnName;
        this.templateColumnAlias = templateColumnAlias;
        this.tableName = templateTableName;
        this.tableAlias = templateTableAlias;
        this.columnName = templateColumnName;
        this.columnAlias = templateColumnAlias;
    }

    public AbstractSqlPartDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.templateTableName = templateTableName;
        this.templateTableAlias = templateTableAlias;
        this.templateColumnName = templateColumnName;
        this.templateColumnAlias = templateColumnAlias;
        this.tableName = templateTableName;
        this.tableAlias = templateTableAlias;
        this.columnName = templateColumnName;
        this.columnAlias = mappingFieldName;
    }

    @SuppressWarnings("unchecked")
    public R setTableName(String tableName) {
        this.tableName = tableName;
        return (R) this;
    }

    @SuppressWarnings("unchecked")
    public R setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return (R) this;
    }

    @SuppressWarnings("unchecked")
    public R setColumnName(String columnName) {
        this.columnName = columnName;
        return (R) this;
    }

    @SuppressWarnings("unchecked")
    public R setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
        return (R) this;
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
