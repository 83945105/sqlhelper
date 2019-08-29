package pub.avalon.sqlhelper.core.option;

import pub.avalon.sqlhelper.core.beans.ComparisonRule;

/**
 * @author baichao
 */
public final class SqlPartDatumBuilderOptions {

    public final static SqlPartDatumBuilderOptions SQL_PART_DATUM_BUILDER_OPTIONS = new SqlPartDatumBuilderOptions();

    private ComparisonRule defaultWhereComparisonRule = ComparisonRule.NULL_SKIP;

    private ComparisonRule defaultOnComparisonRule = ComparisonRule.NULL_SKIP;

    public ComparisonRule getDefaultWhereComparisonRule() {
        return defaultWhereComparisonRule;
    }

    public SqlPartDatumBuilderOptions setDefaultWhereComparisonRule(ComparisonRule defaultWhereComparisonRule) {
        this.defaultWhereComparisonRule = defaultWhereComparisonRule;
        return this;
    }

    public ComparisonRule getDefaultOnComparisonRule() {
        return defaultOnComparisonRule;
    }

    public SqlPartDatumBuilderOptions setDefaultOnComparisonRule(ComparisonRule defaultOnComparisonRule) {
        this.defaultOnComparisonRule = defaultOnComparisonRule;
        return this;
    }
}