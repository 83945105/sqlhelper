package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.helper.Helper;

import java.util.Collection;

/**
 * @author baichao
 */
public interface BaseComparisonOperator<T> {

    /**
     * get default comparison rule.
     *
     * @return {@link ComparisonRule}
     */
    ComparisonRule getDefaultComparisonRule();

    /**
     * custom sql
     *
     * @param targetSqlPart sql part
     * @return extends {@link Helper} object
     */
    T sqlPart(String targetSqlPart);

    /**
     * is null
     *
     * @return extends {@link Helper} object
     */
    T isNull();

    /**
     * is not null
     *
     * @return extends {@link Helper} object
     */
    T isNotNull();

    /**
     * equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T equalTo(Object value, ComparisonRule comparisonRule);

    /**
     * equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T equalTo(Object value) {
        return equalTo(value, getDefaultComparisonRule());
    }

    /**
     * not equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T notEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * not equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T notEqualTo(Object value) {
        return notEqualTo(value, getDefaultComparisonRule());
    }

    /**
     * greater than
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T greaterThan(Object value, ComparisonRule comparisonRule);

    /**
     * greater than
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T greaterThan(Object value) {
        return greaterThan(value, getDefaultComparisonRule());
    }

    /**
     * greater than or equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * greater than or equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T greaterThanAndEqualTo(Object value) {
        return greaterThanAndEqualTo(value, getDefaultComparisonRule());
    }

    /**
     * less than
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lessThan(Object value, ComparisonRule comparisonRule);

    /**
     * less than
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lessThan(Object value) {
        return lessThan(value, getDefaultComparisonRule());
    }

    /**
     * less than or equal to
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * less than or equal to
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T lessThanAndEqualTo(Object value) {
        return lessThanAndEqualTo(value, getDefaultComparisonRule());
    }

    /**
     * between ... and ...
     *
     * @param value          comparison start value
     * @param secondValue    comparison end value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T between(Object value, Object secondValue, ComparisonRule comparisonRule);

    /**
     * between ... and ...
     *
     * @param value       comparison start value
     * @param secondValue comparison end value
     * @return extends {@link Helper} object
     */
    default T between(Object value, Object secondValue) {
        return between(value, secondValue, getDefaultComparisonRule());
    }

    /**
     * like
     *
     * @param value          comparison value
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T like(Object value, ComparisonRule comparisonRule);

    /**
     * like
     *
     * @param value comparison value
     * @return extends {@link Helper} object
     */
    default T like(Object value) {
        return like(value, getDefaultComparisonRule());
    }

    /**
     * in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T in(Object[] values, ComparisonRule comparisonRule);

    /**
     * in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    default T in(ComparisonRule comparisonRule, Object... values) {
        return in(values, comparisonRule);
    }

    /**
     * in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T in(Object... values) {
        return in(getDefaultComparisonRule(), values);
    }

    /**
     * in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T in(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T in(Collection<Object> values) {
        return in(values, getDefaultComparisonRule());
    }

    /**
     * not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T notIn(Object[] values, ComparisonRule comparisonRule);

    /**
     * not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    default T notIn(ComparisonRule comparisonRule, Object... values) {
        return notIn(values, comparisonRule);
    }

    /**
     * not in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T notIn(Object... values) {
        return notIn(getDefaultComparisonRule(), values);
    }

    /**
     * not in
     *
     * @param values         comparison values
     * @param comparisonRule {@link ComparisonRule}
     * @return extends {@link Helper} object
     */
    T notIn(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * not in
     *
     * @param values comparison values
     * @return extends {@link Helper} object
     */
    default T notIn(Collection<Object> values) {
        return notIn(values, getDefaultComparisonRule());
    }
}