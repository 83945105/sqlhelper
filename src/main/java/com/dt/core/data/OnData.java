package com.dt.core.data;

import com.dt.core.bean.OnType;
import com.dt.core.bean.OnValueType;

/**
 * 条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnData {

    private String ownerTableName;

    private String ownerTableAlias;

    private String ownerColumnName;

    private OnType onType = OnType.EQUAL;

    private OnValueType onValueType = OnValueType.VALUE;

    private String targetTableName;

    private String targetAlias;

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

    public String getTargetAlias() {
        return targetAlias;
    }

    public void setTargetAlias(String targetAlias) {
        this.targetAlias = targetAlias;
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
}
