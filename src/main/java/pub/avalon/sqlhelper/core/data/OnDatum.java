package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.OnType;

import java.util.List;

/**
 * @author baichao
 */
public final class OnDatum extends AbstractSqlPartDatum<OnDatum> {

    private OnType onType = OnType.EQUAL;

    private Type type = Type.VALUE;

    private List<OnDatum> targetOnData;

    private List<ColumnDatum> targetColumnData;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    private String sqlPart;

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.type = Type.SQL_PART;
    }

    public OnType getOnType() {
        return onType;
    }

    public Type getType() {
        return type;
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

    public String getSqlPart() {
        return sqlPart;
    }

    public OnDatum setOnType(OnType onType) {
        this.onType = onType;
        return this;
    }

    public OnDatum setType(Type type) {
        this.type = type;
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

    public enum Type {
        /**
         * join other {@link OnDatum}
         */
        JOIN_ON,
        /**
         * join other {@link ColumnDatum}
         */
        JOIN_COLUMN,
        /**
         * specific value
         */
        VALUE,
        /**
         * custom sql value
         */
        SQL_PART
    }
}
