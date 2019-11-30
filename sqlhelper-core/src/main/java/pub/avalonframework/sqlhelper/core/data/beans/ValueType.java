package pub.avalonframework.sqlhelper.core.data.beans;

/**
 * @author baichao
 */
public enum ValueType {
    /**
     * none value
     */
    NONE_VALUE,
    /**
     * single value
     */
    SINGLE_VALUE,
    /**
     * pair value
     */
    PAIR_VALUE,
    /**
     * multi value
     */
    MULTI_VALUE,
    /**
     * sub query value
     */
    SUB_QUERY,
    /**
     * custom sql value
     */
    SQL_PART,
    /**
     * single sql part datum
     */
    SINGLE_SQL_PART_DATUM,
    /**
     * pair sql part datum
     */
    PAIR_SQL_PART_DATUM,
    /**
     * multi sql part datum
     */
    MULTI_SQL_PART_DATUM
}