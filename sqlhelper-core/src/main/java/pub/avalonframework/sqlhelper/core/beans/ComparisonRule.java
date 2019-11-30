package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.exception.ComparisonRuleNullException;

/**
 * @author baichao
 */
public enum ComparisonRule {
    /**
     * when the parameter is null,the current rule is invalid.
     * {@link pub.avalon.sqlhelper.core.comparison.ComparisonOperator}
     */
    NULL_SKIP,
    /**
     * when the parameter is null,throw exception.
     * {@link pub.avalon.sqlhelper.core.comparison.ComparisonOperator}
     * {@link ComparisonRuleNullException}
     */
    NULL_THROW_EXCEPTION
}