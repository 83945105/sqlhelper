package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * 条件数据
 *
 * @author baichao
 * @since 2018/7/10
 */
public final class WhereDatum extends AbstractSqlPartDatum<WhereDatum> {

    private WhereType whereType = WhereType.EQUAL;

    private Type type = Type.VALUE;

    private List<WhereDatum> targetWhereData;

    private List<ColumnDatum> targetColumnData;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    private SqlBuilderResult targetSubQuery;

    private String sqlPart;

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.type = Type.SQL_PART;
    }

    public WhereDatum setWhereType(WhereType whereType) {
        this.whereType = whereType;
        return this;
    }

    public WhereDatum setType(Type type) {
        this.type = type;
        return this;
    }

    public WhereDatum setTargetWhereData(List<WhereDatum> targetWhereData) {
        this.targetWhereData = targetWhereData;
        return this;
    }

    public WhereDatum setTargetColumnData(List<ColumnDatum> targetColumnData) {
        this.targetColumnData = targetColumnData;
        return this;
    }

    public WhereDatum setTargetValue(Object targetValue) {
        this.targetValue = targetValue;
        return this;
    }

    public WhereDatum setTargetSecondValue(Object targetSecondValue) {
        this.targetSecondValue = targetSecondValue;
        return this;
    }

    public WhereDatum setValueCount(int valueCount) {
        this.valueCount = valueCount;
        return this;
    }

    public WhereDatum setTargetSubQuery(SqlBuilderResult targetSubQuery) {
        this.targetSubQuery = targetSubQuery;
        return this;
    }

    public WhereDatum setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
        return this;
    }

    public WhereType getWhereType() {
        return whereType;
    }

    public Type getType() {
        return type;
    }

    public List<WhereDatum> getTargetWhereData() {
        return targetWhereData;
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

    public SqlBuilderResult getTargetSubQuery() {
        return targetSubQuery;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public enum Type {
        /**
         * join other {@link WhereDatum}
         */
        JOIN_WHERE,
        /**
         * join other {@link ColumnDatum}
         */
        JOIN_COLUMN,
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
        SQL_PART
    }
}
