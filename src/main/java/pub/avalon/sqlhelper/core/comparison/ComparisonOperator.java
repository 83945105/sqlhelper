package pub.avalon.sqlhelper.core.comparison;

import pub.avalon.sqlhelper.core.beans.ComparisonRule;

import java.util.Collection;

/**
 * 比较运算符
 *
 * @author baichao
 * @since 2018/7/10
 */
public interface ComparisonOperator<T> {

    /**
     * 获取默认比较规则
     *
     * @return {@link ComparisonRule}
     */
    ComparisonRule getDefaultComparisonRule();

    /**
     * 为null
     *
     * @return 条件对象
     */
    T isNull();

    /**
     * 不为null
     *
     * @return 条件对象
     */
    T isNotNull();

    /**
     * 等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T equalTo(Object value, ComparisonRule comparisonRule);

    /**
     * 等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T equalTo(Object value) {
        return equalTo(value, getDefaultComparisonRule());
    }

    /**
     * 不等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T notEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * 不等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T notEqualTo(Object value) {
        return notEqualTo(value, getDefaultComparisonRule());
    }

    /**
     * 大于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T greaterThan(Object value, ComparisonRule comparisonRule);

    /**
     * 大于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThan(Object value) {
        return greaterThan(value, getDefaultComparisonRule());
    }

    /**
     * 大于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T greaterThanAndEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * 大于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Object value) {
        return greaterThanAndEqualTo(value, getDefaultComparisonRule());
    }

    /**
     * 小于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T lessThan(Object value, ComparisonRule comparisonRule);

    /**
     * 小于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThan(Object value) {
        return lessThan(value, getDefaultComparisonRule());
    }

    /**
     * 小于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T lessThanAndEqualTo(Object value, ComparisonRule comparisonRule);

    /**
     * 小于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Object value) {
        return lessThanAndEqualTo(value, getDefaultComparisonRule());
    }

    /**
     * 介于
     *
     * @param value          比较开始值
     * @param secondValue    比较结束值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T between(Object value, Object secondValue, ComparisonRule comparisonRule);

    /**
     * 介于
     *
     * @param value       比较开始值
     * @param secondValue 比较结束值
     * @return 条件对象
     */
    default T between(Object value, Object secondValue) {
        return between(value, secondValue, getDefaultComparisonRule());
    }

    /**
     * 模糊查询
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T like(Object value, ComparisonRule comparisonRule);

    /**
     * 模糊查询
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T like(Object value) {
        return like(value, getDefaultComparisonRule());
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T in(Object[] values, ComparisonRule comparisonRule);

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(ComparisonRule comparisonRule, Object... values) {
        return in(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T in(Object... values) {
        return in(getDefaultComparisonRule(), values);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T in(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T in(Collection<Object> values) {
        return in(values, getDefaultComparisonRule());
    }

    /**
     * 不在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T notIn(Object[] values, ComparisonRule comparisonRule);

    /**
     * 不在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T notIn(ComparisonRule comparisonRule, Object... values) {
        return notIn(values, comparisonRule);
    }

    /**
     * 不在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T notIn(Object... values) {
        return notIn(getDefaultComparisonRule(), values);
    }

    /**
     * 不在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T notIn(Collection<Object> values, ComparisonRule comparisonRule);

    /**
     * 不在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T notIn(Collection<Object> values) {
        return notIn(values, getDefaultComparisonRule());
    }

}
