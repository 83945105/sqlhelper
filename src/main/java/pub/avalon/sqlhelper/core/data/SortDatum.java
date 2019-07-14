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

    private String templateTableName;

    private String templateTableAlias;

    private String templateColumnName;

    private String templateColumnAlias;

    private String mappingFieldName;

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    private SortType sortType = SortType.ASC;

    public SortDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
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

    public SortDatum setTemplateTableName(String templateTableName) {
        this.templateTableName = templateTableName;
        return this;
    }

    public SortDatum setTemplateTableAlias(String templateTableAlias) {
        this.templateTableAlias = templateTableAlias;
        return this;
    }

    public SortDatum setTemplateColumnName(String templateColumnName) {
        this.templateColumnName = templateColumnName;
        return this;
    }

    public SortDatum setTemplateColumnAlias(String templateColumnAlias) {
        this.templateColumnAlias = templateColumnAlias;
        return this;
    }

    public SortDatum setMappingFieldName(String mappingFieldName) {
        this.mappingFieldName = mappingFieldName;
        return this;
    }

    public SortDatum setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SortDatum setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public SortDatum setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public SortDatum setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
        return this;
    }

    public SortDatum setSortType(SortType sortType) {
        this.sortType = sortType;
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

    public SortType getSortType() {
        return sortType;
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
        return Objects.equals(getTemplateTableName(), sortDatum.getTemplateTableName()) &&
                Objects.equals(getTemplateTableAlias(), sortDatum.getTemplateTableAlias()) &&
                Objects.equals(getTemplateColumnName(), sortDatum.getTemplateColumnName()) &&
                Objects.equals(getTemplateColumnAlias(), sortDatum.getTemplateColumnAlias()) &&
                Objects.equals(getMappingFieldName(), sortDatum.getMappingFieldName()) &&
                Objects.equals(getTableName(), sortDatum.getTableName()) &&
                Objects.equals(getTableAlias(), sortDatum.getTableAlias()) &&
                Objects.equals(getColumnName(), sortDatum.getColumnName()) &&
                Objects.equals(getColumnAlias(), sortDatum.getColumnAlias()) &&
                getSortType() == sortDatum.getSortType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemplateTableName(), getTemplateTableAlias(), getTemplateColumnName(), getTemplateColumnAlias(), getMappingFieldName(), getTableName(), getTableAlias(), getColumnName(), getColumnAlias(), getSortType());
    }
}
