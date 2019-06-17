package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.OnType;
import pub.avalon.sqlhelper.core.beans.OnValueType;

import java.util.Objects;

/**
 * 条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnDatum implements SqlPartDatum {

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private String ownerColumnAlias;

    private String ownerMappingFieldName;

    private OnType onType = OnType.EQUAL;

    private OnValueType onValueType = OnValueType.VALUE;

    private String targetTableName;

    private String targetTableAlias;

    private String targetColumnName;

    private String targetColumnAlias;

    private String targetMappingFieldName;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    public OnDatum(String ownerTableName, String ownerTableAlias, String ownerColumnName, String ownerColumnAlias, String ownerMappingFieldName) {
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

    public OnType getOnType() {
        return onType;
    }

    public void setOnType(OnType onType) {
        this.onType = onType;
    }

    public OnValueType getOnValueType() {
        return onValueType;
    }

    public void setOnValueType(OnValueType onValueType) {
        this.onValueType = onValueType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OnDatum onDatum = (OnDatum) o;
        return Objects.equals(getOwnerTableName(), onDatum.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), onDatum.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), onDatum.getOwnerColumnName()) &&
                Objects.equals(getOwnerColumnAlias(), onDatum.getOwnerColumnAlias()) &&
                Objects.equals(getOwnerMappingFieldName(), onDatum.getOwnerMappingFieldName()) &&
                getOnType() == onDatum.getOnType() &&
                getOnValueType() == onDatum.getOnValueType() &&
                Objects.equals(getTargetTableName(), onDatum.getTargetTableName()) &&
                Objects.equals(getTargetTableAlias(), onDatum.getTargetTableAlias()) &&
                Objects.equals(getTargetColumnName(), onDatum.getTargetColumnName()) &&
                Objects.equals(getTargetColumnAlias(), onDatum.getTargetColumnAlias()) &&
                Objects.equals(getTargetMappingFieldName(), onDatum.getTargetMappingFieldName()) &&
                Objects.equals(getTargetValue(), onDatum.getTargetValue()) &&
                Objects.equals(getTargetSecondValue(), onDatum.getTargetSecondValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getOwnerColumnAlias(), getOwnerMappingFieldName(), getOnType(), getOnValueType(), getTargetTableName(), getTargetTableAlias(), getTargetColumnName(), getTargetColumnAlias(), getTargetMappingFieldName(), getTargetValue(), getTargetSecondValue());
    }

}
