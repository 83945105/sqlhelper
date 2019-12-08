package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.beans.ColumnType;
import pub.avalonframework.sqlhelper.core.data.beans.Type;
import pub.avalonframework.sqlhelper.core.data.beans.ValueType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractComparisonSqlPartDatum<T extends AbstractComparisonSqlPartDatum<T>> extends AbstractSqlPartDatum<T> {

    protected Type type = Type.DEFAULT;

    protected String sqlPart;

    protected ColumnType columnType = ColumnType.DEFAULT;

    // 已经尝试过ColumnHandler[] columnHandlers, 这么做后面解析过于麻烦, 放弃
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

    public AbstractComparisonSqlPartDatum<T> setSqlPart(String sqlPart) {
        if (sqlPart == null) {
            return this;
        }
        this.type = Type.SQL_PART;
        this.sqlPart = sqlPart;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setColumnHandler(ColumnHandler columnHandler) {
        if (columnHandler == null) {
            return this;
        }
        this.columnType = ColumnType.HANDLER;
        this.columnHandler = columnHandler;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetNoneValue(ComparisonType comparisonType) {
        if (comparisonType == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.NONE_VALUE;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        if (comparisonType == null || targetValue == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SINGLE_VALUE;
        this.targetValue = targetValue;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        if (comparisonType == null || targetValue == null || targetSecondValue == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.PAIR_VALUE;
        this.targetValue = targetValue;
        this.targetSecondValue = targetSecondValue;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        if (comparisonType == null || targetValue == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.MULTI_VALUE;
        this.targetValue = targetValue;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        if (comparisonType == null || targetSubQuery == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SUB_QUERY;
        this.targetSubQuery = targetSubQuery;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetSqlPart(String targetSqlPart) {
        if (targetSqlPart == null) {
            return this;
        }
        this.comparisonType = ComparisonType.NONE;
        this.valueType = ValueType.SQL_PART;
        this.targetSqlPart = targetSqlPart;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetSingleSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum) {
        if (comparisonType == null || targetSqlPartDatum == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.SINGLE_SQL_PART_DATUM;
        this.targetSqlPartDatum = targetSqlPartDatum;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetPairSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum, AbstractSqlPartDatum targetSecondSqlPartDatum) {
        if (comparisonType == null || targetSqlPartDatum == null || targetSecondSqlPartDatum == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.PAIR_SQL_PART_DATUM;
        this.targetSqlPartDatum = targetSqlPartDatum;
        this.targetSecondSqlPartDatum = targetSecondSqlPartDatum;
        return this;
    }

    public AbstractComparisonSqlPartDatum<T> setTargetMultiSqlPartDatum(ComparisonType comparisonType, List<AbstractSqlPartDatum> targetMultiSqlPartDatum) {
        if (comparisonType == null || targetMultiSqlPartDatum == null) {
            return this;
        }
        this.comparisonType = comparisonType;
        this.valueType = ValueType.MULTI_SQL_PART_DATUM;
        this.targetMultiSqlPartDatum = targetMultiSqlPartDatum;
        return this;
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