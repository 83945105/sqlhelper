package pub.avalon.sqlhelper.core.data;

import java.util.List;

/**
 * @author baichao
 */
public final class WhereDatum extends AbstractComparisonSqlPartDatum<WhereDatum> {

    private List<WhereDatum> targetWhereData;

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    public WhereDatum setTargetWhereData(ComparisonType comparisonType, List<WhereDatum> targetWhereData) {
        this.valueType = ValueTypeEnum.JOIN_WHERE;
        this.comparisonType = comparisonType;
        this.targetWhereData = targetWhereData;
        return this;
    }

    public List<WhereDatum> getTargetWhereData() {
        return targetWhereData;
    }

    public enum ValueTypeEnum implements ValueType {
        /**
         * join other {@link WhereDatum}
         */
        JOIN_WHERE
    }
}