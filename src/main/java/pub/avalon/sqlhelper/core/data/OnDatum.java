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

    private String ownerTemplateTableName;

    private String ownerTemplateTableAlias;

    private String ownerTemplateColumnName;

    private String ownerTemplateColumnAlias;

    private String ownerMappingFieldName;

    private String targetTemplateTableName;

    private String targetTemplateTableAlias;

    private String targetTemplateColumnName;

    private String targetTemplateColumnAlias;

    private String targetMappingFieldName;

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private String ownerColumnAlias;

    private OnType onType = OnType.EQUAL;

    private OnValueType onValueType = OnValueType.VALUE;

    private String targetTableName;

    private String targetTableAlias;

    private String targetColumnName;

    private String targetColumnAlias;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    public OnDatum(String ownerTemplateTableName, String ownerTemplateTableAlias, String ownerTemplateColumnName, String ownerTemplateColumnAlias, String ownerMappingFieldName) {
        this.ownerTemplateTableName = ownerTemplateTableName;
        this.ownerTemplateTableAlias = ownerTemplateTableAlias;
        this.ownerTemplateColumnName = ownerTemplateColumnName;
        this.ownerTemplateColumnAlias = ownerTemplateColumnAlias;
        this.ownerMappingFieldName = ownerMappingFieldName;
        this.ownerTableName = ownerTemplateTableName;
        this.ownerTableAlias = ownerTemplateTableAlias;
        this.ownerColumnName = ownerTemplateColumnName;
        this.ownerColumnAlias = ownerTemplateColumnAlias;
    }

    public OnDatum setOwnerTemplateTableName(String ownerTemplateTableName) {
        this.ownerTemplateTableName = ownerTemplateTableName;
        return this;
    }

    public OnDatum setOwnerTemplateTableAlias(String ownerTemplateTableAlias) {
        this.ownerTemplateTableAlias = ownerTemplateTableAlias;
        return this;
    }

    public OnDatum setOwnerTemplateColumnName(String ownerTemplateColumnName) {
        this.ownerTemplateColumnName = ownerTemplateColumnName;
        return this;
    }

    public OnDatum setOwnerTemplateColumnAlias(String ownerTemplateColumnAlias) {
        this.ownerTemplateColumnAlias = ownerTemplateColumnAlias;
        return this;
    }

    public OnDatum setOwnerMappingFieldName(String ownerMappingFieldName) {
        this.ownerMappingFieldName = ownerMappingFieldName;
        return this;
    }

    public OnDatum setTargetTemplateTableName(String targetTemplateTableName) {
        this.targetTemplateTableName = targetTemplateTableName;
        return this;
    }

    public OnDatum setTargetTemplateTableAlias(String targetTemplateTableAlias) {
        this.targetTemplateTableAlias = targetTemplateTableAlias;
        return this;
    }

    public OnDatum setTargetTemplateColumnName(String targetTemplateColumnName) {
        this.targetTemplateColumnName = targetTemplateColumnName;
        return this;
    }

    public OnDatum setTargetTemplateColumnAlias(String targetTemplateColumnAlias) {
        this.targetTemplateColumnAlias = targetTemplateColumnAlias;
        return this;
    }

    public OnDatum setTargetMappingFieldName(String targetMappingFieldName) {
        this.targetMappingFieldName = targetMappingFieldName;
        return this;
    }

    public OnDatum setOwnerTableName(String ownerTableName) {
        this.ownerTableName = ownerTableName;
        return this;
    }

    public OnDatum setOwnerTableAlias(String ownerTableAlias) {
        this.ownerTableAlias = ownerTableAlias;
        return this;
    }

    public OnDatum setOwnerColumnName(String ownerColumnName) {
        this.ownerColumnName = ownerColumnName;
        return this;
    }

    public OnDatum setOwnerColumnAlias(String ownerColumnAlias) {
        this.ownerColumnAlias = ownerColumnAlias;
        return this;
    }

    public OnDatum setOnType(OnType onType) {
        this.onType = onType;
        return this;
    }

    public OnDatum setOnValueType(OnValueType onValueType) {
        this.onValueType = onValueType;
        return this;
    }

    public OnDatum setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
        return this;
    }

    public OnDatum setTargetTableAlias(String targetTableAlias) {
        this.targetTableAlias = targetTableAlias;
        return this;
    }

    public OnDatum setTargetColumnName(String targetColumnName) {
        this.targetColumnName = targetColumnName;
        return this;
    }

    public OnDatum setTargetColumnAlias(String targetColumnAlias) {
        this.targetColumnAlias = targetColumnAlias;
        return this;
    }

    public OnDatum setTargetValue(Object targetValue) {
        this.targetValue = targetValue;
        return this;
    }

    public OnDatum setTargetSecondValue(Object targetSecondValue) {
        this.targetSecondValue = targetSecondValue;
        return this;
    }

    public OnDatum setValueCount(int valueCount) {
        this.valueCount = valueCount;
        return this;
    }

    public String getOwnerTemplateTableName() {
        return ownerTemplateTableName;
    }

    public String getOwnerTemplateTableAlias() {
        return ownerTemplateTableAlias;
    }

    public String getOwnerTemplateColumnName() {
        return ownerTemplateColumnName;
    }

    public String getOwnerTemplateColumnAlias() {
        return ownerTemplateColumnAlias;
    }

    public String getOwnerMappingFieldName() {
        return ownerMappingFieldName;
    }

    public String getTargetTemplateTableName() {
        return targetTemplateTableName;
    }

    public String getTargetTemplateTableAlias() {
        return targetTemplateTableAlias;
    }

    public String getTargetTemplateColumnName() {
        return targetTemplateColumnName;
    }

    public String getTargetTemplateColumnAlias() {
        return targetTemplateColumnAlias;
    }

    public String getTargetMappingFieldName() {
        return targetMappingFieldName;
    }

    public String getOwnerTableName() {
        return ownerTableName;
    }

    public String getOwnerTableAlias() {
        return ownerTableAlias;
    }

    public String getOwnerColumnName() {
        return ownerColumnName;
    }

    public String getOwnerColumnAlias() {
        return ownerColumnAlias;
    }

    public OnType getOnType() {
        return onType;
    }

    public OnValueType getOnValueType() {
        return onValueType;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public String getTargetTableAlias() {
        return targetTableAlias;
    }

    public String getTargetColumnName() {
        return targetColumnName;
    }

    public String getTargetColumnAlias() {
        return targetColumnAlias;
    }

    public Object getTargetValue() {
        return targetValue;
    }

    public Object getTargetSecondValue() {
        return targetSecondValue;
    }

    public int getValueCount() {
        return valueCount;
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
        return getValueCount() == onDatum.getValueCount() &&
                Objects.equals(getOwnerTemplateTableName(), onDatum.getOwnerTemplateTableName()) &&
                Objects.equals(getOwnerTemplateTableAlias(), onDatum.getOwnerTemplateTableAlias()) &&
                Objects.equals(getOwnerTemplateColumnName(), onDatum.getOwnerTemplateColumnName()) &&
                Objects.equals(getOwnerTemplateColumnAlias(), onDatum.getOwnerTemplateColumnAlias()) &&
                Objects.equals(getOwnerMappingFieldName(), onDatum.getOwnerMappingFieldName()) &&
                Objects.equals(getTargetTemplateTableName(), onDatum.getTargetTemplateTableName()) &&
                Objects.equals(getTargetTemplateTableAlias(), onDatum.getTargetTemplateTableAlias()) &&
                Objects.equals(getTargetTemplateColumnName(), onDatum.getTargetTemplateColumnName()) &&
                Objects.equals(getTargetTemplateColumnAlias(), onDatum.getTargetTemplateColumnAlias()) &&
                Objects.equals(getTargetMappingFieldName(), onDatum.getTargetMappingFieldName()) &&
                Objects.equals(getOwnerTableName(), onDatum.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), onDatum.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), onDatum.getOwnerColumnName()) &&
                Objects.equals(getOwnerColumnAlias(), onDatum.getOwnerColumnAlias()) &&
                getOnType() == onDatum.getOnType() &&
                getOnValueType() == onDatum.getOnValueType() &&
                Objects.equals(getTargetTableName(), onDatum.getTargetTableName()) &&
                Objects.equals(getTargetTableAlias(), onDatum.getTargetTableAlias()) &&
                Objects.equals(getTargetColumnName(), onDatum.getTargetColumnName()) &&
                Objects.equals(getTargetColumnAlias(), onDatum.getTargetColumnAlias()) &&
                Objects.equals(getTargetValue(), onDatum.getTargetValue()) &&
                Objects.equals(getTargetSecondValue(), onDatum.getTargetSecondValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerTemplateTableName(), getOwnerTemplateTableAlias(), getOwnerTemplateColumnName(), getOwnerTemplateColumnAlias(), getOwnerMappingFieldName(), getTargetTemplateTableName(), getTargetTemplateTableAlias(), getTargetTemplateColumnName(), getTargetTemplateColumnAlias(), getTargetMappingFieldName(), getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getOwnerColumnAlias(), getOnType(), getOnValueType(), getTargetTableName(), getTargetTableAlias(), getTargetColumnName(), getTargetColumnAlias(), getTargetValue(), getTargetSecondValue(), getValueCount());
    }
}
