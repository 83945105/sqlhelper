package pub.avalon.sqlhelper.core.data;

import java.util.List;

/**
 * @author baichao
 */
public final class OnDatum extends AbstractComparisonSqlPartDatum<OnDatum> {

    private List<OnDatum> targetOnData;

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    public OnDatum setTargetOnData(ComparisonType comparisonType, List<OnDatum> targetOnData) {
        this.valueType = ValueTypeEnum.JOIN_ON;
        this.comparisonType = comparisonType;
        this.targetOnData = targetOnData;
        return this;
    }

    public List<OnDatum> getTargetOnData() {
        return targetOnData;
    }

    public enum ValueTypeEnum implements ValueType {
        /**
         * join other {@link OnDatum}
         */
        JOIN_ON
    }
}