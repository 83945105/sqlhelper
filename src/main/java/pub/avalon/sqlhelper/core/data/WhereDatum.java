package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.beans.WhereValueType;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;

import java.util.Map;
import java.util.Objects;

/**
 * 条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereDatum {

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private WhereType whereType = WhereType.EQUAL;

    private WhereValueType whereValueType = WhereValueType.VALUE;

    private String targetTableName;

    private String targetTableAlias;

    private String targetColumnName;

    private String targetSecondColumnName;

    private Map<String, String> targetColumnNames;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    private SqlBuilder targetSubQuery;

    private String sqlPart;

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
        WhereDatum whereData = (WhereDatum) o;
        return getValueCount() == whereData.getValueCount() &&
                Objects.equals(getOwnerTableName(), whereData.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), whereData.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), whereData.getOwnerColumnName()) &&
                getWhereType() == whereData.getWhereType() &&
                getWhereValueType() == whereData.getWhereValueType() &&
                Objects.equals(getTargetTableName(), whereData.getTargetTableName()) &&
                Objects.equals(getTargetTableAlias(), whereData.getTargetTableAlias()) &&
                Objects.equals(getTargetColumnName(), whereData.getTargetColumnName()) &&
                Objects.equals(getTargetSecondColumnName(), whereData.getTargetSecondColumnName()) &&
                Objects.equals(getTargetColumnNames(), whereData.getTargetColumnNames()) &&
                Objects.equals(getTargetValue(), whereData.getTargetValue()) &&
                Objects.equals(getTargetSecondValue(), whereData.getTargetSecondValue()) &&
                Objects.equals(getTargetSubQuery(), whereData.getTargetSubQuery()) &&
                Objects.equals(getSqlPart(), whereData.getSqlPart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getWhereType(), getWhereValueType(), getTargetTableName(), getTargetTableAlias(), getTargetColumnName(), getTargetSecondColumnName(), getTargetColumnNames(), getTargetValue(), getTargetSecondValue(), getValueCount(), getTargetSubQuery(), getSqlPart());
    }

}
