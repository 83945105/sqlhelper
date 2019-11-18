package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.beans.ColumnType;
import pub.avalon.sqlhelper.core.data.beans.Type;
import pub.avalon.sqlhelper.core.data.beans.ValueType;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractComparisonSqlPartDatum<T extends AbstractComparisonSqlPartDatum<T>> extends AbstractSqlPartDatum<T> {

    protected Type type = Type.DEFAULT;

    protected String sqlPart;

    protected ColumnType columnType = ColumnType.DEFAULT;

    protected ColumnHandler columnHandler;

    protected ComparisonType comparisonType = ComparisonType.NONE;

    protected ValueType valueType = ValueType.SINGLE_VALUE;

    protected Object targetValue;

    protected Object targetSecondValue;

    protected SqlBuilderResult targetSubQuery;

    protected String targetSqlPart;

    protected AbstractSqlPartDatum targetSqlPartDatum;

    protected AbstractSqlPartDatum targetSecondSqlPartDatum;

    protected List<AbstractSqlPartDatum> targetMultiSqlPartDatum;

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
        this.type = Type.SQL_PART;
        this.sqlPart = sqlPart;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setColumnHandler(ColumnHandler columnHandler) {
        this.columnType = ColumnType.HANDLER;
        this.columnHandler = columnHandler;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetNoneValue(ComparisonType comparisonType) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.NONE_VALUE;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SINGLE_VALUE;
        this.targetValue = targetValue;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.PAIR_VALUE;
        this.targetValue = targetValue;
        this.targetSecondValue = targetSecondValue;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.MULTI_VALUE;
        this.targetValue = targetValue;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SUB_QUERY;
        this.targetSubQuery = targetSubQuery;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetSqlPart(String targetSqlPart) {
        this.comparisonType = ComparisonType.NONE;
        this.valueType = ValueType.SQL_PART;
        this.targetSqlPart = targetSqlPart;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetSingleSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SINGLE_SQL_PART_DATUM;
        this.targetSqlPartDatum = targetSqlPartDatum;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetPairSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum, AbstractSqlPartDatum targetSecondSqlPartDatum) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.PAIR_SQL_PART_DATUM;
        this.targetSqlPartDatum = targetSqlPartDatum;
        this.targetSecondSqlPartDatum = targetSecondSqlPartDatum;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTargetMultiSqlPartDatum(ComparisonType comparisonType, List<AbstractSqlPartDatum> targetMultiSqlPartDatum) {
        this.comparisonType = comparisonType;
        this.valueType = ValueType.MULTI_SQL_PART_DATUM;
        this.targetMultiSqlPartDatum = targetMultiSqlPartDatum;
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

    public ColumnHandler getColumnHandler() {
        return columnHandler;
    }

    public ComparisonType getComparisonType() {
        return comparisonType;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public Object getTargetValue() {
        return targetValue;
    }

    public Object getTargetSecondValue() {
        return targetSecondValue;
    }

    public SqlBuilderResult getTargetSubQuery() {
        return targetSubQuery;
    }

    public String getTargetSqlPart() {
        return targetSqlPart;
    }

    public AbstractSqlPartDatum getTargetSqlPartDatum() {
        return targetSqlPartDatum;
    }

    public AbstractSqlPartDatum getTargetSecondSqlPartDatum() {
        return targetSecondSqlPartDatum;
    }

    public List<AbstractSqlPartDatum> getTargetMultiSqlPartDatum() {
        return targetMultiSqlPartDatum;
    }
}