package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.beans.WhereValueType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.Map;
import java.util.Objects;

/**
 * 条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereDatum implements SqlPartDatum {

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private String ownerColumnAlias;

    private String ownerMappingFieldName;

    private WhereType whereType = WhereType.EQUAL;

    private WhereValueType whereValueType = WhereValueType.VALUE;

    private String targetTableName;

    private String targetTableAlias;

    private String targetColumnName;

    private String targetColumnAlias;

    private String targetMappingFieldName;

    private String targetSecondColumnName;

    private Map<String, String> targetColumnNames;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    private SqlBuilder targetSubQuery;

    private String sqlPart;

    public WhereDatum(String ownerTableName, String ownerTableAlias, String ownerColumnName, String ownerColumnAlias, String ownerMappingFieldName) {
        this.ownerTableName = ownerTableName;
        this.ownerTableAlias = ownerTableAlias;
        this.ownerColumnName = ownerColumnName;
        this.ownerColumnAlias = ownerColumnAlias;
        this.ownerMappingFieldName = ownerMappingFieldName;
    }

    public String getOwnerTableName() {
        return ownerTableName;
    }

    public void setOwnerTableName(String ownerTableName) {
        this.ownerTableName = ownerTableName;
    }

    public String getOwnerTableAlias() {
        return ownerTableAlias;
    }

    public void setOwnerTableAlias(String ownerTableAlias) {
        this.ownerTableAlias = ownerTableAlias;
    }

    public String getOwnerColumnName() {
        return ownerColumnName;
    }

    public void setOwnerColumnName(String ownerColumnName) {
        this.ownerColumnName = ownerColumnName;
    }

    public String getOwnerColumnAlias() {
        return ownerColumnAlias;
    }

    public void setOwnerColumnAlias(String ownerColumnAlias) {
        this.ownerColumnAlias = ownerColumnAlias;
    }

    public String getOwnerMappingFieldName() {
        return ownerMappingFieldName;
    }

    public void setOwnerMappingFieldName(String ownerMappingFieldName) {
        this.ownerMappingFieldName = ownerMappingFieldName;
    }

    public WhereType getWhereType() {
        return whereType;
    }

    public void setWhereType(WhereType whereType) {
        this.whereType = whereType;
    }

    public WhereValueType getWhereValueType() {
        return whereValueType;
    }

    public void setWhereValueType(WhereValueType whereValueType) {
        this.whereValueType = whereValueType;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public String getTargetTableAlias() {
        return targetTableAlias;
    }

    public void setTargetTableAlias(String targetTableAlias) {
        this.targetTableAlias = targetTableAlias;
    }

    public String getTargetColumnName() {
        return targetColumnName;
    }

    public void setTargetColumnName(String targetColumnName) {
        this.targetColumnName = targetColumnName;
    }

    public String getTargetColumnAlias() {
        return targetColumnAlias;
    }

    public void setTargetColumnAlias(String targetColumnAlias) {
        this.targetColumnAlias = targetColumnAlias;
    }

    public String getTargetMappingFieldName() {
        return targetMappingFieldName;
    }

    public void setTargetMappingFieldName(String targetMappingFieldName) {
        this.targetMappingFieldName = targetMappingFieldName;
    }

    public String getTargetSecondColumnName() {
        return targetSecondColumnName;
    }

    public void setTargetSecondColumnName(String targetSecondColumnName) {
        this.targetSecondColumnName = targetSecondColumnName;
    }

    public Map<String, String> getTargetColumnNames() {
        return targetColumnNames;
    }

    public void setTargetColumnNames(Map<String, String> targetColumnNames) {
        this.targetColumnNames = targetColumnNames;
    }

    public Object getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Object targetValue) {
        this.targetValue = targetValue;
    }

    public Object getTargetSecondValue() {
        return targetSecondValue;
    }

    public void setTargetSecondValue(Object targetSecondValue) {
        this.targetSecondValue = targetSecondValue;
    }

    public int getValueCount() {
        return valueCount;
    }

    public void setValueCount(int valueCount) {
        this.valueCount = valueCount;
    }

    public SqlBuilder getTargetSubQuery() {
        return targetSubQuery;
    }

    public void setTargetSubQuery(SqlBuilder targetSubQuery) {
        this.targetSubQuery = targetSubQuery;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WhereDatum that = (WhereDatum) o;
        return Objects.equals(getOwnerTableName(), that.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), that.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), that.getOwnerColumnName()) &&
                Objects.equals(getOwnerColumnAlias(), that.getOwnerColumnAlias()) &&
                Objects.equals(getOwnerMappingFieldName(), that.getOwnerMappingFieldName()) &&
                getWhereType() == that.getWhereType() &&
                getWhereValueType() == that.getWhereValueType() &&
                Objects.equals(getTargetTableName(), that.getTargetTableName()) &&
                Objects.equals(getTargetTableAlias(), that.getTargetTableAlias()) &&
                Objects.equals(getTargetColumnName(), that.getTargetColumnName()) &&
                Objects.equals(getTargetColumnAlias(), that.getTargetColumnAlias()) &&
                Objects.equals(getTargetMappingFieldName(), that.getTargetMappingFieldName()) &&
                Objects.equals(getTargetSecondColumnName(), that.getTargetSecondColumnName()) &&
                Objects.equals(getTargetColumnNames(), that.getTargetColumnNames()) &&
                Objects.equals(getTargetValue(), that.getTargetValue()) &&
                Objects.equals(getTargetSecondValue(), that.getTargetSecondValue()) &&
                Objects.equals(getTargetSubQuery(), that.getTargetSubQuery()) &&
                Objects.equals(getSqlPart(), that.getSqlPart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getOwnerColumnAlias(), getOwnerMappingFieldName(), getWhereType(), getWhereValueType(), getTargetTableName(), getTargetTableAlias(), getTargetColumnName(), getTargetColumnAlias(), getTargetMappingFieldName(), getTargetSecondColumnName(), getTargetColumnNames(), getTargetValue(), getTargetSecondValue(), getTargetSubQuery(), getSqlPart());
    }

}
