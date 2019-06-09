package pub.avalon.sqlhelper.generator.beans;

import pub.avalon.sqlhelper.generator.option.GenerateOptions;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 白超
 * @date 2019/6/9
 */
public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表别名
     */
    private String tableAlias;

    /**
     * 主键列
     */
    private Column primaryKeyColumn;

    /**
     * 所有列
     */
    private List<Column> columns;

    /**
     * 生成配置
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

}
