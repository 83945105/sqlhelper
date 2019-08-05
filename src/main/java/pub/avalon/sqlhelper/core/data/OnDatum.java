package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.OnType;
import pub.avalon.sqlhelper.core.beans.OnValueType;

import java.util.List;

/**
 * 条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnDatum extends AbstractSqlPartDatum<OnDatum> {

    private OnType onType = OnType.EQUAL;

    private OnValueType onValueType = OnValueType.VALUE;

    private List<OnDatum> targetOnData;

    private List<ColumnDatum> targetColumnData;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public OnDatum setOnType(OnType onType) {
        this.onType = onType;
        return this;
    }

    public OnDatum setOnValueType(OnValueType onValueType) {
        this.onValueType = onValueType;
        return this;
    }

    public OnDatum setTargetOnData(List<OnDatum> targetOnData) {
        this.targetOnData = targetOnData;
        return this;
    }

    public OnDatum setTargetColumnData(List<ColumnDatum> targetColumnData) {
        this.targetColumnData = targetColumnData;
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

    public OnType getOnType() {
        return onType;
    }

    public OnValueType getOnValueType() {
        return onValueType;
    }

    public List<OnDatum> getTargetOnData() {
        return targetOnData;
    }

    public List<ColumnDatum> getTargetColumnData() {
        return targetColumnData;
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

}
