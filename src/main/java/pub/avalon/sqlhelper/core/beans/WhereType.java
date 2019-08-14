package pub.avalon.sqlhelper.core.beans;

/**
 * 条件类型
 *
 * @author baichao
 * @since 2018/7/10
 */
public enum WhereType {
    /**
     * 为空
     */
    IS_NULL,
    /**
     * 不为空
     */
    IS_NOT_NULL,
    /**
     * 等于
     */
    EQUAL,
    /**
     * 不等于
     */
    NOT_EQUAL,
    /**
     * 大于
     */
    GREATER,
    /**
     * 大于等于
     */
    GREATER_EQUAL,
    /**
     * 小于
     */
    LESS,
    /**
     * 小于等于
     */
    LESS_EQUAL,
    /**
     * 介于
     */
    BETWEEN,
    /**
     * 模糊匹配
     */
    LIKE,
    /**
     * 在...内
     */
    IN,
    /**
     * 不在...内
     */
    NOT_IN
}
