package pub.avalonframework.sqlhelper.core.data;

import org.springframework.beans.BeanUtils;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;

import java.util.List;

/**
 * @author baichao
 */
public final class WhereDatum extends AbstractComparisonSqlPartDatum<WhereDatum> {

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    @Override
    public WhereDatum setSqlPart(String sqlPart) {
        super.setSqlPart(sqlPart);
        return this;
    }

    @Override
    public WhereDatum setColumnHandler(ColumnHandler columnHandler) {
        super.setColumnHandler(columnHandler);
        return this;
    }

    @Override
    public WhereDatum setTargetNoneValue(ComparisonType comparisonType) {
        super.setTargetNoneValue(comparisonType);
        return this;
    }

    @Override
    public WhereDatum setTargetSingleValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetSingleValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public WhereDatum setTargetPairValue(ComparisonType comparisonType, Object targetValue, Object targetSecondValue) {
        super.setTargetPairValue(comparisonType, targetValue, targetSecondValue);
        return this;
    }

    @Override
    public WhereDatum setTargetMultiValue(ComparisonType comparisonType, Object targetValue) {
        super.setTargetMultiValue(comparisonType, targetValue);
        return this;
    }

    @Override
    public WhereDatum setTargetSubQuery(ComparisonType comparisonType, SqlBuilderResult targetSubQuery) {
        super.setTargetSubQuery(comparisonType, targetSubQuery);
        return this;
    }

    @Override
    public WhereDatum setTargetSqlPart(String targetSqlPart) {
        super.setTargetSqlPart(targetSqlPart);
        return this;
    }

    @Override
    public WhereDatum setTargetSingleSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum) {
        super.setTargetSingleSqlPartDatum(comparisonType, targetSqlPartDatum);
        return this;
    }

    @Override
    public WhereDatum setTargetPairSqlPartDatum(ComparisonType comparisonType, AbstractSqlPartDatum targetSqlPartDatum, AbstractSqlPartDatum targetSecondSqlPartDatum) {
        super.setTargetPairSqlPartDatum(comparisonType, targetSqlPartDatum, targetSecondSqlPartDatum);
        return this;
    }

    @Override
    public WhereDatum setTargetMultiSqlPartDatum(ComparisonType comparisonType, List<AbstractSqlPartDatum> targetMultiSqlPartDatum) {
        super.setTargetMultiSqlPartDatum(comparisonType, targetMultiSqlPartDatum);
        return this;
    }

    @Override
    public WhereDatum setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public WhereDatum setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public WhereDatum setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public WhereDatum setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }

    public WhereDatum getCloneComparisonSqlPartDatum() {
        WhereDatum whereDatum = new WhereDatum(this.getTemplateTableName(), this.getTemplateTableAlias(), this.getTemplateColumnName(), this.getTemplateColumnAlias());
        BeanUtils.copyProperties(this, whereDatum);
        return whereDatum;
    }
}