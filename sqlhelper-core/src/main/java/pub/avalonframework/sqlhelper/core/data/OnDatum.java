package pub.avalonframework.sqlhelper.core.data;

import org.springframework.beans.BeanUtils;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public final class OnDatum extends AbstractComparisonSqlPartDatum<OnDatum> {

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    @Override
    public OnDatum setSqlPart(String sqlPart) {
        super.setSqlPart(sqlPart);
        return this;
    }

    @Override
    public OnDatum setColumnHandler(ColumnHandler columnHandler) {
        super.setColumnHandler(columnHandler);
        return this;
    }

    @Override
    public OnDatum setTargetNoneValue(ComparisonType comparisonType) {
        super.setTargetNoneValue(comparisonType);
        return this;
    }

    @Override
    public OnDatum setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetSingleValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public OnDatum setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        super.setTargetPairValue(comparisonType, targetValue, targetSecondValue);
        return this;
    }

    @Override
    public OnDatum setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetMultiValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public OnDatum setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        super.setTargetSubQuery(comparisonType, targetSubQuery);
        return this;
    }

    @Override
    public OnDatum setTargetSqlPart(String targetSqlPart) {
        super.setTargetSqlPart(targetSqlPart);
        return this;
    }

    @Override
    public OnDatum setTargetSingleSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum) {
        super.setTargetSingleSqlPartDatum(comparisonType, targetSqlPartDatum);
        return this;
    }

    @Override
    public OnDatum setTargetPairSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum, AbstractSqlPartDatum targetSecondSqlPartDatum) {
        super.setTargetPairSqlPartDatum(comparisonType, targetSqlPartDatum, targetSecondSqlPartDatum);
        return this;
    }

    @Override
    public OnDatum setTargetMultiSqlPartDatum(ComparisonType comparisonType, List<AbstractSqlPartDatum> targetMultiSqlPartDatum) {
        super.setTargetMultiSqlPartDatum(comparisonType, targetMultiSqlPartDatum);
        return this;
    }

    @Override
    public OnDatum setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public OnDatum setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public OnDatum setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public OnDatum setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }

    public OnDatum getCloneComparisonSqlPartDatum() {
        OnDatum onDatum = new OnDatum(this.getTemplateTableName(), this.getTemplateTableAlias(), this.getTemplateColumnName(), this.getTemplateColumnAlias());
        BeanUtils.copyProperties(this, onDatum);
        return onDatum;
    }
}