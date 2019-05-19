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
public final class OnDatum implements SqlModelDatum {

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private OnType onType = OnType.EQUAL;

    private OnValueType onValueType = OnValueType.VALUE;

    private String targetTableName;

    private String targetTableAlias;

    private String targetColumnName;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

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
        OnDatum onData = (OnDatum) o;
        return getValueCount() == onData.getValueCount() &&
                Objects.equals(getOwnerTableName(), onData.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), onData.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), onData.getOwnerColumnName()) &&
                getOnType() == onData.getOnType() &&
                getOnValueType() == onData.getOnValueType() &&
                Objects.equals(getTargetTableName(), onData.getTargetTableName()) &&
                Objects.equals(getTargetTableAlias(), onData.getTargetTableAlias()) &&
                Objects.equals(getTargetColumnName(), onData.getTargetColumnName()) &&
                Objects.equals(getTargetValue(), onData.getTargetValue()) &&
                Objects.equals(getTargetSecondValue(), onData.getTargetSecondValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getOnType(), getOnValueType(), getTargetTableName(), getTargetTableAlias(), getTargetColumnName(), getTargetValue(), getTargetSecondValue(), getValueCount());
    }
}
