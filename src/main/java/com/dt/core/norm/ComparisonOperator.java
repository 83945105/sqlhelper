package com.dt.core.norm;

import com.dt.core.bean.ComparisonRule;

import java.util.Collection;

/**
 * 比较运算符
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface ComparisonOperator<T> {

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
    T equalToValue(Object value, ComparisonRule comparisonRule);

    /**
     * 等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T equalTo(String value) {
        return equalToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T equalTo(Integer value) {
        return equalToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T equalTo(Long value) {
        return equalToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T equalTo(Double value) {
        return equalToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T equalTo(String value, ComparisonRule comparisonRule) {
        return equalToValue(value, comparisonRule);
    }

    /**
     * 等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T equalTo(Integer value, ComparisonRule comparisonRule) {
        return equalToValue(value, comparisonRule);
    }

    /**
     * 等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T equalTo(Long value, ComparisonRule comparisonRule) {
        return equalToValue(value, comparisonRule);
    }

    /**
     * 等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T equalTo(Double value, ComparisonRule comparisonRule) {
        return equalToValue(value, comparisonRule);
    }

    /**
     * 不等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T notEqualToValue(Object value, ComparisonRule comparisonRule);

    /**
     * 不等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T notEqualTo(String value) {
        return notEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 不等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T notEqualTo(Integer value) {
        return notEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 不等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T notEqualTo(Long value) {
        return notEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 不等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T notEqualTo(Double value) {
        return notEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 不等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T notEqualTo(String value, ComparisonRule comparisonRule) {
        return notEqualToValue(value, comparisonRule);
    }

    /**
     * 不等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T notEqualTo(Integer value, ComparisonRule comparisonRule) {
        return notEqualToValue(value, comparisonRule);
    }

    /**
     * 不等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T notEqualTo(Long value, ComparisonRule comparisonRule) {
        return notEqualToValue(value, comparisonRule);
    }

    /**
     * 不等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T notEqualTo(Double value, ComparisonRule comparisonRule) {
        return notEqualToValue(value, comparisonRule);
    }

    /**
     * 大于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T greaterThanValue(Object value, ComparisonRule comparisonRule);

    /**
     * 大于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThan(String value) {
        return greaterThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThan(Integer value) {
        return greaterThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThan(Long value) {
        return greaterThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThan(Double value) {
        return greaterThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThan(String value, ComparisonRule comparisonRule) {
        return greaterThanValue(value, comparisonRule);
    }

    /**
     * 大于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThan(Integer value, ComparisonRule comparisonRule) {
        return greaterThanValue(value, comparisonRule);
    }

    /**
     * 大于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThan(Long value, ComparisonRule comparisonRule) {
        return greaterThanValue(value, comparisonRule);
    }

    /**
     * 大于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThan(Double value, ComparisonRule comparisonRule) {
        return greaterThanValue(value, comparisonRule);
    }

    /**
     * 大于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule);

    /**
     * 大于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(String value) {
        return greaterThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Integer value) {
        return greaterThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Long value) {
        return greaterThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Double value) {
        return greaterThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 大于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(String value, ComparisonRule comparisonRule) {
        return greaterThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 大于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Integer value, ComparisonRule comparisonRule) {
        return greaterThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 大于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Long value, ComparisonRule comparisonRule) {
        return greaterThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 大于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T greaterThanAndEqualTo(Double value, ComparisonRule comparisonRule) {
        return greaterThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 小于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T lessThanValue(Object value, ComparisonRule comparisonRule);

    /**
     * 小于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThan(String value) {
        return lessThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThan(Integer value) {
        return lessThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThan(Long value) {
        return lessThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThan(Double value) {
        return lessThanValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThan(String value, ComparisonRule comparisonRule) {
        return lessThanValue(value, comparisonRule);
    }

    /**
     * 小于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThan(Integer value, ComparisonRule comparisonRule) {
        return lessThanValue(value, comparisonRule);
    }

    /**
     * 小于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThan(Long value, ComparisonRule comparisonRule) {
        return lessThanValue(value, comparisonRule);
    }

    /**
     * 小于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThan(Double value, ComparisonRule comparisonRule) {
        return lessThanValue(value, comparisonRule);
    }

    /**
     * 小于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule);

    /**
     * 小于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThanAndEqualTo(String value) {
        return lessThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Integer value) {
        return lessThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Long value) {
        return lessThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于等于
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Double value) {
        return lessThanAndEqualToValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 小于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThanAndEqualTo(String value, ComparisonRule comparisonRule) {
        return lessThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 小于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Integer value, ComparisonRule comparisonRule) {
        return lessThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 小于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Long value, ComparisonRule comparisonRule) {
        return lessThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 小于等于
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T lessThanAndEqualTo(Double value, ComparisonRule comparisonRule) {
        return lessThanAndEqualToValue(value, comparisonRule);
    }

    /**
     * 介于
     *
     * @param value          比较开始值
     * @param secondValue    比较结束值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule);

    /**
     * 介于
     *
     * @param value       比较开始值
     * @param secondValue 比较结束值
     * @return 条件对象
     */
    default T between(String value, String secondValue) {
        return betweenValue(value, secondValue, ComparisonRule.NULL_SKIP);
    }

    /**
     * 介于
     *
     * @param value       比较开始值
     * @param secondValue 比较结束值
     * @return 条件对象
     */
    default T between(Integer value, Integer secondValue) {
        return betweenValue(value, secondValue, ComparisonRule.NULL_SKIP);
    }

    /**
     * 介于
     *
     * @param value       比较开始值
     * @param secondValue 比较结束值
     * @return 条件对象
     */
    default T between(Long value, Long secondValue) {
        return betweenValue(value, secondValue, ComparisonRule.NULL_SKIP);
    }

    /**
     * 介于
     *
     * @param value       比较开始值
     * @param secondValue 比较结束值
     * @return 条件对象
     */
    default T between(Double value, Double secondValue) {
        return betweenValue(value, secondValue, ComparisonRule.NULL_SKIP);
    }

    /**
     * 介于
     *
     * @param value          比较开始值
     * @param secondValue    比较结束值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T between(String value, String secondValue, ComparisonRule comparisonRule) {
        return betweenValue(value, secondValue, comparisonRule);
    }

    /**
     * 介于
     *
     * @param value          比较开始值
     * @param secondValue    比较结束值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T between(Integer value, Integer secondValue, ComparisonRule comparisonRule) {
        return betweenValue(value, secondValue, comparisonRule);
    }

    /**
     * 介于
     *
     * @param value          比较开始值
     * @param secondValue    比较结束值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T between(Long value, Long secondValue, ComparisonRule comparisonRule) {
        return betweenValue(value, secondValue, comparisonRule);
    }

    /**
     * 介于
     *
     * @param value          比较开始值
     * @param secondValue    比较结束值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T between(Double value, Double secondValue, ComparisonRule comparisonRule) {
        return betweenValue(value, secondValue, comparisonRule);
    }

    /**
     * 模糊查询
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T likeValue(Object value, ComparisonRule comparisonRule);

    /**
     * 模糊查询
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T like(String value) {
        return likeValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 模糊查询
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T like(Integer value) {
        return likeValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 模糊查询
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T like(Long value) {
        return likeValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 模糊查询
     *
     * @param value 比较值
     * @return 条件对象
     */
    default T like(Double value) {
        return likeValue(value, ComparisonRule.NULL_SKIP);
    }

    /**
     * 模糊查询
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T like(String value, ComparisonRule comparisonRule) {
        return likeValue(value, comparisonRule);
    }

    /**
     * 模糊查询
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T like(Integer value, ComparisonRule comparisonRule) {
        return likeValue(value, comparisonRule);
    }

    /**
     * 模糊查询
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T like(Long value, ComparisonRule comparisonRule) {
        return likeValue(value, comparisonRule);
    }

    /**
     * 模糊查询
     *
     * @param value          比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T like(Double value, ComparisonRule comparisonRule) {
        return likeValue(value, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T inValue(Object[] values, ComparisonRule comparisonRule);

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T in(String[] values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T in(Integer[] values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T in(Long[] values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T in(Double[] values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(String[] values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(Integer[] values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(Long[] values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(Double[] values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(ComparisonRule comparisonRule, String... values) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(ComparisonRule comparisonRule, Integer... values) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(ComparisonRule comparisonRule, Long... values) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T in(ComparisonRule comparisonRule, Double... values) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    T inValue(Collection<?> values, ComparisonRule comparisonRule);

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T inS(Collection<String> values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T inI(Collection<Integer> values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T inL(Collection<Long> values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values 比较值
     * @return 条件对象
     */
    default T inD(Collection<Double> values) {
        return inValue(values, ComparisonRule.NULL_SKIP);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T inS(Collection<String> values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T inI(Collection<Integer> values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T inL(Collection<Long> values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }

    /**
     * 在...内
     *
     * @param values         比较值
     * @param comparisonRule 比较规则
     * @return 条件对象
     */
    default T inD(Collection<Double> values, ComparisonRule comparisonRule) {
        return inValue(values, comparisonRule);
    }
}
