package pub.avalonframework.sqlhelper.generator.beans;

import pub.avalonframework.sqlhelper.generator.options.GenerateOptions;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * The table.
 *
 * @author baichao
 */
public class Table {

    /**
     * The table name.
     */
    private String tableName;

    /**
     * The table alias.
     */
    private String tableAlias;

    /**
     * The primary key column.
     */
    private Column primaryKeyColumn;

    /**
     * The columns.
     */
    private List<Column> columns;

    /**
     * The generate options.
     */
    private GenerateOptions generateOptions;

    public String getTableName() {
        return tableName;
    }

    public Table setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Table setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public Column getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public Table setPrimaryKeyColumn(Column primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
        return this;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Table setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    public GenerateOptions getGenerateOptions() {
        return generateOptions;
    }

    public Table setGenerateOptions(GenerateOptions generateOptions) {
        this.generateOptions = generateOptions;
        return this;
    }

    public JavaType getJavaType(Column column) {
        return this.generateOptions.getTypeConverter().converterJdbcType(column.getType());
    }

    public String getColumnAlias(Column column) {
        return this.generateOptions.getStringConverter().converterColumnName(column.getName());
    }

    public String getGetterMethodName(Column column) {
        boolean isBoolean = this.getGenerateOptions().getTypeConverter().converterJdbcType(column.getType()) == JavaType.Boolean ||
                this.getGenerateOptions().getTypeConverter().converterJdbcType(column.getType()) == JavaType._boolean;
        return this.getGenerateOptions().getStringConverter().converterColumnGetterMethodName(column.getName(), isBoolean);
    }

    public String getSetterMethodName(Column column) {
        return this.getGenerateOptions().getStringConverter().converterColumnSetterMethodName(column.getName());
    }

    public Set<String> getClassPaths() {
        Set<String> classPaths = new LinkedHashSet<>();
        List<Column> columns = this.getColumns();
        TypeConverter typeConverter = this.getGenerateOptions().getTypeConverter();
        if (columns != null) {
            for (Column column : columns) {
                String classPath = typeConverter.converterJdbcType(column.getType()).getClassPath();
                if (classPath == null) {
                    continue;
                }
                classPaths.add(classPath);
            }
        }
        classPaths.addAll(this.getGenerateOptions().getClassPaths());
        return classPaths;
    }

    public String getTableHelperJavaFileName() {
        return getTableAlias() + this.generateOptions.getTableHelperOptions().getClassNameSuffix();
    }

    public String getEntityJavaFileName() {
        return getTableAlias() + this.getGenerateOptions().getEntityOptions().getClassNameSuffix();
    }
}