package com.dt.core.bean;

/**
 * 条件类型
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public enum WhereType {
    /**为空*/
    IS_NULL,
    /**不为空*/
    IS_NOT_NULL,
    /**等于*/
    EQUAL,
    /**不等于*/
    NOT_EQUAL,
    /**大于*/
    GREATER,
    /**大于等于*/
    GREATER_EQUAL,
    /**小于*/
    LESS,
    /**小于等于*/
    LESS_EQUAL,
    /**介于*/
    BETWEEN,
    /**模糊匹配*/
    LIKE,
    /**在...内*/
    IN
}
