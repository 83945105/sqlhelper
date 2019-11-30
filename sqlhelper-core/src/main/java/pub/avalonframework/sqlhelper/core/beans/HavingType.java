package pub.avalonframework.sqlhelper.core.beans;

/**
 * @author baichao
 */
public enum HavingType {
    /**
     * is null
     */
    IS_NULL,
    /**
     * is not null
     */
    IS_NOT_NULL,
    /**
     * equal to
     */
    EQUAL,
    /**
     * not equal to
     */
    NOT_EQUAL,
    /**
     * greater than
     */
    GREATER,
    /**
     * greater than or equal to
     */
    GREATER_EQUAL,
    /**
     * less than
     */
    LESS,
    /**
     * less than or equal to
     */
    LESS_EQUAL,
    /**
     * between ... and ...
     */
    BETWEEN,
    /**
     * like
     */
    LIKE,
    /**
     * in
     */
    IN,
    /**
     * not in
     */
    NOT_IN
}