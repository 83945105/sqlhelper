package pub.avalon.sqlhelper.core.beans;

/**
 * Where值类型
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public enum WhereValueType {
    /**
     * 连接其它条件字段
     */
    JOIN_WHERE,
    /**
     * 连接其它列字段
     */
    JOIN_COLUMN,
    /**
     * 具体值
     */
    VALUE,
    /**
     * 子查询
     */
    SUB_QUERY,
    /**
     * Sql片段
     */
    SQL_PART
}
