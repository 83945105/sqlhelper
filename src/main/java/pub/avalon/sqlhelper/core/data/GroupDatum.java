package pub.avalon.sqlhelper.core.data;

import java.util.Objects;

/**
 * 分组数据
 *
 * @author 白超
 * @date 2019/5/6
 */
public class GroupDatum implements SqlPartDatum {

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    private String mappingFieldName;

    public GroupDatum(String tableName, String tableAlias, String columnName, String columnAlias, String mappingFieldName) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
        this.mappingFieldName = mappingFieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public void setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
    }

    public String getMappingFieldName() {
        return mappingFieldName;
    }

    public void setMappingFieldName(String mappingFieldName) {
        this.mappingFieldName = mappingFieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupDatum that = (GroupDatum) o;
        return Objects.equals(getTableName(), that.getTableName()) &&
                Objects.equals(getTableAlias(), that.getTableAlias()) &&
                Objects.equals(getColumnName(), that.getColumnName()) &&
                Objects.equals(getColumnAlias(), that.getColumnAlias()) &&
                Objects.equals(getMappingFieldName(), that.getMappingFieldName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableName(), getTableAlias(), getColumnName(), getColumnAlias(), getMappingFieldName());
    }

}
