package pub.avalon.sqlhelper.core.option;

import pub.avalon.sqlhelper.core.beans.ComparisonRule;

/**
 * Sql片段数据构建器配置
 *
 * @author 白超
 * @date 2019/6/19
 */
public final class SqlPartDatumBuilderOptions {

    public final static SqlPartDatumBuilderOptions SQL_PART_DATUM_BUILDER_OPTIONS = new SqlPartDatumBuilderOptions();

    /**
     * 默认条件比较规则
     * {@link ComparisonRule}
     */
    private ComparisonRule defaultWhereComparisonRule = ComparisonRule.NULL_SKIP;

    /**
     * 默认On比较规则
     */
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
