package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.beans.WhereValueType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * 分组条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class HavingDatum implements SqlPartDatum {

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

    private WhereType whereType = WhereType.EQUAL;

    private WhereValueType whereValueType = WhereValueType.VALUE;

    private String targetTableName;

    private String targetTableAlias;

    private String targetColumnName;

    private String targetColumnAlias;

    private String targetSecondTableName;

    private String targetSecondTableAlias;

    private String targetSecondColumnName;

    private String targetSecondColumnAlias;

    private String targetSecondMappingFieldName;

    private HavingDatum[] targetWhereData;

    private Set<ColumnDatum> targetColumnData;

    private Object targetValue;

    private Object targetSecondValue;

    private int valueCount;

    private SqlBuilder targetSubQuery;

    private String sqlPart;

    public HavingDatum(String ownerTemplateTableName, String ownerTemplateTableAlias, String ownerTemplateColumnName, String ownerTemplateColumnAlias, String ownerMappingFieldName) {
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

    public HavingDatum setOwnerTemplateTableName(String ownerTemplateTableName) {
        this.ownerTemplateTableName = ownerTemplateTableName;
        return this;
    }

    public HavingDatum setOwnerTemplateTableAlias(String ownerTemplateTableAlias) {
        this.ownerTemplateTableAlias = ownerTemplateTableAlias;
        return this;
    }

    public HavingDatum setOwnerTemplateColumnName(String ownerTemplateColumnName) {
        this.ownerTemplateColumnName = ownerTemplateColumnName;
        return this;
    }

    public HavingDatum setOwnerTemplateColumnAlias(String ownerTemplateColumnAlias) {
        this.ownerTemplateColumnAlias = ownerTemplateColumnAlias;
        return this;
    }

    public HavingDatum setOwnerMappingFieldName(String ownerMappingFieldName) {
        this.ownerMappingFieldName = ownerMappingFieldName;
        return this;
    }

    public HavingDatum setTargetTemplateTableName(String targetTemplateTableName) {
        this.targetTemplateTableName = targetTemplateTableName;
        return this;
    }

    public HavingDatum setTargetTemplateTableAlias(String targetTemplateTableAlias) {
        this.targetTemplateTableAlias = targetTemplateTableAlias;
        return this;
    }

    public HavingDatum setTargetTemplateColumnName(String targetTemplateColumnName) {
        this.targetTemplateColumnName = targetTemplateColumnName;
        return this;
    }

    public HavingDatum setTargetTemplateColumnAlias(String targetTemplateColumnAlias) {
        this.targetTemplateColumnAlias = targetTemplateColumnAlias;
        return this;
    }

    public HavingDatum setTargetMappingFieldName(String targetMappingFieldName) {
        this.targetMappingFieldName = targetMappingFieldName;
        return this;
    }

    public HavingDatum setOwnerTableName(String ownerTableName) {
        this.ownerTableName = ownerTableName;
        return this;
    }

    public HavingDatum setOwnerTableAlias(String ownerTableAlias) {
        this.ownerTableAlias = ownerTableAlias;
        return this;
    }

    public HavingDatum setOwnerColumnName(String ownerColumnName) {
        this.ownerColumnName = ownerColumnName;
        return this;
    }

    public HavingDatum setOwnerColumnAlias(String ownerColumnAlias) {
        this.ownerColumnAlias = ownerColumnAlias;
        return this;
    }

    public HavingDatum setWhereType(WhereType whereType) {
        this.whereType = whereType;
        return this;
    }

    public HavingDatum setWhereValueType(WhereValueType whereValueType) {
        this.whereValueType = whereValueType;
        return this;
    }

    public HavingDatum setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
        return this;
    }

    public HavingDatum setTargetTableAlias(String targetTableAlias) {
        this.targetTableAlias = targetTableAlias;
        return this;
    }

    public HavingDatum setTargetColumnName(String targetColumnName) {
        this.targetColumnName = targetColumnName;
        return this;
    }

    public HavingDatum setTargetColumnAlias(String targetColumnAlias) {
        this.targetColumnAlias = targetColumnAlias;
        return this;
    }

    public HavingDatum setTargetSecondTableName(String targetSecondTableName) {
        this.targetSecondTableName = targetSecondTableName;
        return this;
    }

    public HavingDatum setTargetSecondTableAlias(String targetSecondTableAlias) {
        this.targetSecondTableAlias = targetSecondTableAlias;
        return this;
    }

    public HavingDatum setTargetSecondColumnName(String targetSecondColumnName) {
        this.targetSecondColumnName = targetSecondColumnName;
        return this;
    }

    public HavingDatum setTargetSecondColumnAlias(String targetSecondColumnAlias) {
        this.targetSecondColumnAlias = targetSecondColumnAlias;
        return this;
    }

    public HavingDatum setTargetSecondMappingFieldName(String targetSecondMappingFieldName) {
        this.targetSecondMappingFieldName = targetSecondMappingFieldName;
        return this;
    }

    public HavingDatum setTargetWhereData(HavingDatum[] targetWhereData) {
        this.targetWhereData = targetWhereData;
        return this;
    }

    public HavingDatum setTargetColumnData(Set<ColumnDatum> targetColumnData) {
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

    public WhereType getWhereType() {
        return whereType;
    }

    public WhereValueType getWhereValueType() {
        return whereValueType;
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

    public String getTargetSecondTableName() {
        return targetSecondTableName;
    }

    public String getTargetSecondTableAlias() {
        return targetSecondTableAlias;
    }

    public String getTargetSecondColumnName() {
        return targetSecondColumnName;
    }

    public String getTargetSecondColumnAlias() {
        return targetSecondColumnAlias;
    }

    public String getTargetSecondMappingFieldName() {
        return targetSecondMappingFieldName;
    }

    public HavingDatum[] getTargetWhereData() {
        return targetWhereData;
    }

    public Set<ColumnDatum> getTargetColumnData() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HavingDatum that = (HavingDatum) o;
        return getValueCount() == that.getValueCount() &&
                Objects.equals(getOwnerTemplateTableName(), that.getOwnerTemplateTableName()) &&
                Objects.equals(getOwnerTemplateTableAlias(), that.getOwnerTemplateTableAlias()) &&
                Objects.equals(getOwnerTemplateColumnName(), that.getOwnerTemplateColumnName()) &&
                Objects.equals(getOwnerTemplateColumnAlias(), that.getOwnerTemplateColumnAlias()) &&
                Objects.equals(getOwnerMappingFieldName(), that.getOwnerMappingFieldName()) &&
                Objects.equals(getTargetTemplateTableName(), that.getTargetTemplateTableName()) &&
                Objects.equals(getTargetTemplateTableAlias(), that.getTargetTemplateTableAlias()) &&
                Objects.equals(getTargetTemplateColumnName(), that.getTargetTemplateColumnName()) &&
                Objects.equals(getTargetTemplateColumnAlias(), that.getTargetTemplateColumnAlias()) &&
                Objects.equals(getTargetMappingFieldName(), that.getTargetMappingFieldName()) &&
                Objects.equals(getOwnerTableName(), that.getOwnerTableName()) &&
                Objects.equals(getOwnerTableAlias(), that.getOwnerTableAlias()) &&
                Objects.equals(getOwnerColumnName(), that.getOwnerColumnName()) &&
                Objects.equals(getOwnerColumnAlias(), that.getOwnerColumnAlias()) &&
                getWhereType() == that.getWhereType() &&
                getWhereValueType() == that.getWhereValueType() &&
                Objects.equals(getTargetTableName(), that.getTargetTableName()) &&
                Objects.equals(getTargetTableAlias(), that.getTargetTableAlias()) &&
                Objects.equals(getTargetColumnName(), that.getTargetColumnName()) &&
                Objects.equals(getTargetColumnAlias(), that.getTargetColumnAlias()) &&
                Objects.equals(getTargetSecondTableName(), that.getTargetSecondTableName()) &&
                Objects.equals(getTargetSecondTableAlias(), that.getTargetSecondTableAlias()) &&
                Objects.equals(getTargetSecondColumnName(), that.getTargetSecondColumnName()) &&
                Objects.equals(getTargetSecondColumnAlias(), that.getTargetSecondColumnAlias()) &&
                Objects.equals(getTargetSecondMappingFieldName(), that.getTargetSecondMappingFieldName()) &&
                Arrays.equals(getTargetWhereData(), that.getTargetWhereData()) &&
                Objects.equals(getTargetColumnData(), that.getTargetColumnData()) &&
                Objects.equals(getTargetValue(), that.getTargetValue()) &&
                Objects.equals(getTargetSecondValue(), that.getTargetSecondValue()) &&
                Objects.equals(getTargetSubQuery(), that.getTargetSubQuery()) &&
                Objects.equals(getSqlPart(), that.getSqlPart());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getOwnerTemplateTableName(), getOwnerTemplateTableAlias(), getOwnerTemplateColumnName(), getOwnerTemplateColumnAlias(), getOwnerMappingFieldName(), getTargetTemplateTableName(), getTargetTemplateTableAlias(), getTargetTemplateColumnName(), getTargetTemplateColumnAlias(), getTargetMappingFieldName(), getOwnerTableName(), getOwnerTableAlias(), getOwnerColumnName(), getOwnerColumnAlias(), getWhereType(), getWhereValueType(), getTargetTableName(), getTargetTableAlias(), getTargetColumnName(), getTargetColumnAlias(), getTargetSecondTableName(), getTargetSecondTableAlias(), getTargetSecondColumnName(), getTargetSecondColumnAlias(), getTargetSecondMappingFieldName(), getTargetColumnData(), getTargetValue(), getTargetSecondValue(), getValueCount(), getTargetSubQuery(), getSqlPart());
        result = 31 * result + Arrays.hashCode(getTargetWhereData());
        return result;
    }
}
