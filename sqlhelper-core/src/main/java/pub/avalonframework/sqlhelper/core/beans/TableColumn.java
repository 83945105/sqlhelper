package pub.avalonframework.sqlhelper.core.beans;

import java.util.Objects;
import java.util.Set;

/**
 * @author baichao
 */
public final class TableColumn {

    private String name;

    private String alias;

    private String tableName;

    private String tableAlias;

    public TableColumn() {
    }

    public TableColumn(String name, String alias, String tableName, String tableAlias, TableColumn primaryKeyColumnInfo, Set<TableColumn> tableColumns) {
        this.name = name;
        this.alias = alias;
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.primaryKeyColumnInfo = primaryKeyColumnInfo;
        this.tableColumns = tableColumns;
    }

    private TableColumn primaryKeyColumnInfo;

    private Set<TableColumn> tableColumns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public TableColumn getPrimaryKeyColumnInfo() {
        return primaryKeyColumnInfo;
    }

    public void setPrimaryKeyColumnInfo(TableColumn primaryKeyColumnInfo) {
        this.primaryKeyColumnInfo = primaryKeyColumnInfo;
    }

    public Set<TableColumn> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(Set<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableColumn that = (TableColumn) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}