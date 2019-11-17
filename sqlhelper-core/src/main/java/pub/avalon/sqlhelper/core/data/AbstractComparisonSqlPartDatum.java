package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractComparisonSqlPartDatum<T extends AbstractComparisonSqlPartDatum<T>> extends AbstractSqlPartDatum<T> {

    protected Type type = TypeEnum.DEFAULT;

    protected String sqlPart;

    protected ColumnType columnType = ColumnTypeEnum.DEFAULT;

    protected ValueType valueType = ValueTypeEnum.VALUE;

    protected ComparisonType comparisonType = ComparisonType.NONE;

    protected Object targetValue;

    protected Object targetSecondValue;

    protected long targetValueCount;

    protected SqlBuilderResult targetSubQuery;

    protected String targetSqlPart;

    protected List<ColumnDatum> targetColumnData;

    public AbstractComparisonSqlPartDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public AbstractComparisonSqlPartDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public AbstractComparisonSqlPartDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.setSqlPart(sqlPart);
    }

    @SuppressWarnings("unchecked")
    public T setSqlPart(String sqlPart) {
        this.type = TypeEnum.SQL_PART;
        this.sqlPart = sqlPart;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetValue(ComparisonType comparisonType, Object targetValue, long targetValueCount) {
        this.valueType = ValueTypeEnum.VALUE;
        this.comparisonType = comparisonType;
        this.targetValue = targetValue;
        this.targetValueCount = targetValueCount;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        this.valueType = ValueTypeEnum.VALUE;
        this.comparisonType = comparisonType;
        this.targetValue = targetValue;
        this.targetSecondValue = targetSecondValue;
        this.targetValueCount = 2;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        this.valueType = ValueTypeEnum.SUB_QUERY;
        this.comparisonType = comparisonType;
        this.targetSubQuery = targetSubQuery;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetSqlPart(String targetSqlPart) {
        this.valueType = ValueTypeEnum.SQL_PART;
        this.comparisonType = ComparisonType.NONE;
        this.targetSqlPart = targetSqlPart;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetColumnData(ComparisonType comparisonType, List<ColumnDatum> targetColumnData) {
        this.valueType = ValueTypeEnum.JOIN_COLUMN;
        this.comparisonType = comparisonType;
        this.targetColumnData = targetColumnData;
        return (T) this;
    }

    public Type getType() {
        return type;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public ComparisonType getComparisonType() {
        return comparisonType;
    }

    public Object getTargetValue() {
        return targetValue;
    }

    public Object getTargetSecondValue() {
        return targetSecondValue;
    }

    public long getTargetValueCount() {
        return targetValueCount;
    }

    public SqlBuilderResult getTargetSubQuery() {
        return targetSubQuery;
    }

    public String getTargetSqlPart() {
        return targetSqlPart;
    }

    public List<ColumnDatum> getTargetColumnData() {
        return targetColumnData;
    }

    public enum TypeEnum implements Type {
        /**
         * default type
         */
        DEFAULT,
        /**
         * custom sql
         */
        SQL_PART
    }

    public enum ColumnTypeEnum implements ColumnType {
        /**
         * default type
         */
        DEFAULT
    }

    public enum ValueTypeEnum implements ValueType {
        /**
         * specific value
         */
        VALUE,
        /**
         * sub query value
         */
        SUB_QUERY,
        /**
         * custom sql value
         */
        SQL_PART,
        /**
         * join other {@link WhereDatum}
         */
        JOIN_WHERE,
        /**
         * join other {@link ColumnDatum}
         */
        JOIN_COLUMN
    }
}