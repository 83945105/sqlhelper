package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.SortType;

import java.util.Objects;

/**
 * 排序数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortDatum implements SqlPartDatum {

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    private String mappingFieldName;

    private SortType sortType = SortType.ASC;

    public SortDatum(String tableName, String tableAlias, String columnName, String columnAlias, String mappingFieldName) {
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

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortDatum sortDatum = (SortDatum) o;
        return Objects.equals(getTableName(), sortDatum.getTableName()) &&
                Objects.equals(getTableAlias(), sortDatum.getTableAlias()) &&
                Objects.equals(getColumnName(), sortDatum.getColumnName()) &&
                Objects.equals(getColumnAlias(), sortDatum.getColumnAlias()) &&
                Objects.equals(getMappingFieldName(), sortDatum.getMappingFieldName()) &&
                getSortType() == sortDatum.getSortType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableName(), getTableAlias(), getColumnName(), getColumnAlias(), getMappingFieldName(), getSortType());
    }

}
