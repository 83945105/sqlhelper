package pub.avalon.sqlhelper.core.beans;

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
     * {@link pub.avalon.sqlhelper.core.exception.ComparisonRuleNullException}
     */
    NULL_THROW_EXCEPTION
}
