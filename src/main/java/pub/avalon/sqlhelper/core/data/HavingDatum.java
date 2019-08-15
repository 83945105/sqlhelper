package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.List;

/**
 * @author baichao
 */
public final class HavingDatum extends AbstractSqlPartDatum<HavingDatum> {

    private WhereType whereType = WhereType.EQUAL;

    private HavingDatum[] targetWhereData;

    private List<ColumnDatum> targetColumnData;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    private SqlBuilder targetSubQuery;

    private String sqlPart;

    public HavingDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public HavingDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }


    public HavingDatum setWhereType(WhereType whereType) {
        this.whereType = whereType;
        return this;
    }


    public HavingDatum setTargetWhereData(HavingDatum[] targetWhereData) {
        this.targetWhereData = targetWhereData;
        return this;
    }

    public HavingDatum setTargetColumnData(List<ColumnDatum> targetColumnData) {
        this.targetColumnData = targetColumnData;
        return this;
    }

    public HavingDatum setTargetValue(Object targetValue) {
        this.targetValue = targetValue;
        return this;
    }

    public HavingDatum setTargetSecondValue(Object targetSecondValue) {
        this.targetSecondValue = targetSecondValue;
        return this;
    }

    public HavingDatum setValueCount(int valueCount) {
        this.valueCount = valueCount;
        return this;
    }

    public HavingDatum setTargetSubQuery(SqlBuilder targetSubQuery) {
        this.targetSubQuery = targetSubQuery;
        return this;
    }

    public HavingDatum setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
        return this;
    }

    public WhereType getWhereType() {
        return whereType;
    }


    public HavingDatum[] getTargetWhereData() {
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

    public SqlBuilder getTargetSubQuery() {
        return targetSubQuery;
    }

    public String getSqlPart() {
        return sqlPart;
    }
}